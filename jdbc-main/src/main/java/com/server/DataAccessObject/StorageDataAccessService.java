package com.server.DataAccessObject;

import com.server.Enums.PackageStatus;
import com.server.Enums.VehicleStatus;
import com.server.ModelClass.Package;
import com.server.ModelClass.Storage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StorageDataAccessService implements StorageDao {

    private final JdbcTemplate jdbcTemplate;

    public StorageDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertStorage() {

    }

    @Override
    public List<Storage> getStoragesByCourierID(int courierID) {
        var sql = """
                SELECT *
                FROM courier natural join logisticunit_storage
                WHERE userid = ?
                 """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Storage(
                    resultSet.getInt("storageID"),
                    resultSet.getInt("maxVolume"),
                    resultSet.getInt("currentVolume"),
                    resultSet.getInt("logisticUnitID")
            );
        }, courierID);
    }

    @Override
    public List<Storage> getStoragesEmployeeID(int employeeID) {
        var sql = """
                SELECT *
                FROM employee natural join logisticunit_storage natural join storage
                WHERE userid = ?
                 """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Storage(
                    resultSet.getInt("storageID"),
                    resultSet.getInt("maxVolume"),
                    resultSet.getInt("currentVolume"),
                    resultSet.getInt("logisticUnitID")
            );
        }, employeeID);
    }

    @Override
    public void putPackageIntoStorage(int packageID, int storageID) {
        String sql = """
                INSERT INTO package_storage(PACKAGEID, STORAGEID)
                VALUES (?,?);
                 """;
        jdbcTemplate.update(
                sql,
                packageID,
                storageID
        );
    }
}
