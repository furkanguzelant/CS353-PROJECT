package com.server.DataAccessObject;

import com.server.Enums.PackageStatus;
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
                FROM courier natural join storage
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
                FROM employee natural join storage
                WHERE userid = ?
                 """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Storage(
                    resultSet.getInt("storageID"),
                    resultSet.getInt("maxVolume"),
                    resultSet.getInt("currentVolume"),
                    resultSet.getInt("logisticUnitID")
            );
        }, employeeID)
        ;

    }

    @Override
    public void insertPackageToStorage(int packageID, int storageID) {
        var sql = """
                 UPDATE package
                 SET storageid = ?, status = 2, courierid = null
                 WHERE packageID = ?;
                 """;

        jdbcTemplate.update(
                sql,
                storageID,
                packageID
        );
    }
}
