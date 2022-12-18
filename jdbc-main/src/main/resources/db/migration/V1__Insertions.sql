

INSERT INTO logisticUnit(name, addressid)
values('Karsiyaka Branch', 1);

INSERT INTO logisticUnit(name, addressid)
values('Samsun Distribution Center', 2);

INSERT INTO distributioncenter
values(2);

INSERT INTO logisticUnit(name, addressid)
values('Yeniköy Branch', 3);

INSERT INTO logisticUnit(name, addressid)
values('Samsun Yeşilköy Branch', 4);

INSERT INTO branch
values(4, 2);

INSERT INTO logisticUnit(name, addressid)
values('İzmir Distribution Center', 8);

INSERT INTO distributioncenter
values(5);

INSERT INTO branch
values(1, 5);

SELECT *
FROM logisticunit natural join address



