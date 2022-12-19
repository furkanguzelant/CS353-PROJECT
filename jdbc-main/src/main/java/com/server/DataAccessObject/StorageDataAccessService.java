package com.server.DataAccessObject;

import com.server.Enums.PackageStatus;
import com.server.ModelClass.Package;
import com.server.ModelClass.Storage;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
}
