package com.server.DataAccessObject;

import com.server.Enums.PackageStatus;
import com.server.Enums.VehicleStatus;
import com.server.ModelClass.Package;
import com.server.ModelClass.Vehicle;
import com.server.RowMappers.VehicleRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public class VehicleDataAccessService implements VehicleDao {

    private final JdbcTemplate jdbcTemplate;

    public VehicleDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertVehicle(Vehicle vehicle) {

        String sql = """
                INSERT INTO vehicle(licensePlate,status,maxWeight,currentWeight,courierID,addressID)
                VALUES (?,?,?,?,?,?);
                 """;
        jdbcTemplate.update(
                sql,
                vehicle.getLicensePlate(),
                VehicleStatus.toInteger(vehicle.getStatus()),
                vehicle.getMaxWeight(),
                vehicle.getCurrentWeight(),
                vehicle.getCourierID(),
                vehicle.getAddressID()
        );
    }

    @Override
    public List<Vehicle> selectAllVehicles() {
        var sql = """
                SELECT *
                FROM vehicle
                 """;

        return jdbcTemplate.query(sql, new VehicleRowMapper());
    }

    @Override
    public Optional<Vehicle> getVehicleByLicensePlate(String licensePlate) {

        var sql = """
                SELECT *
                FROM vehicle
                WHERE licensePlate = ?
                 """;


        return jdbcTemplate.query(sql, new VehicleRowMapper(), licensePlate)
                .stream()
                .findFirst();
    }

    @Override
    public List<Package> getPackagesOfVehicle() {
        var sql = """
                SELECT *
                FROM  package
                WHERE licensePlate = ?
                 """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Package(
                    resultSet.getInt("packageID"),
                    resultSet.getInt("weight"),
                    resultSet.getInt("volume"),
                    PackageStatus.fromInteger(
                            resultSet.getInt("status")
                    ),
                    getTagsOfPackage(resultSet.getInt("packageID"))
                    ,
                    resultSet.getInt("senderAddressID"),
                    resultSet.getInt("receiverAddressID"),
                    resultSet.getInt("licencePlate"),
                    resultSet.getInt("customerID"),
                    resultSet.getInt("paymentID")
            );
        });

    }

    List<String> getTagsOfPackage(int packageID) {
        var sql = """
                SELECT distinct tag
                FROM  package natural join package_tag
                WHERE packageID = ?
                 """;
        return jdbcTemplate.query(sql, (resultSet, i) -> {
                    return resultSet.getString("tag");
                },
                packageID
        );

    }
}
