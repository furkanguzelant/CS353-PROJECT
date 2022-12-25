DROP TRIGGER IF EXISTS storage_volume on package;
DROP FUNCTION IF EXISTS updateVolume();

CREATE FUNCTION updateVolume()
    returns TRIGGER
    language plpgsql as
$$
BEGIN
    UPDATE storage
    SET currentvolume = currentvolume + (SELECT volume
                                         FROM package p
                                         WHERE NEW.packageid = p.packageid)
    WHERE storageid = NEW.storageid;

    if OLD.storageid is not null then
        UPDATE storage
        SET currentvolume = currentvolume - (SELECT volume
                                             FROM package p
                                             WHERE OLD.packageid = p.packageid)
        WHERE storageid = OLD.storageid;
    end if;
    RETURN NEW;
END;
$$;

create trigger storage_volume
    after insert
    on package
    FOR EACH ROW
    EXECUTE FUNCTION updateVolume();

create trigger storage_volume_update
    after update of storageid
    on package
    FOR EACH ROW
EXECUTE FUNCTION updateVolume();

