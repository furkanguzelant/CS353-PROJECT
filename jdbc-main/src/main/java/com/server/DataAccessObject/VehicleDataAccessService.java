package com.server.DataAccessObject;

import com.server.Enums.PackageStatus;
import com.server.Enums.VehicleStatus;
import com.server.ModelClass.Package;
import com.server.ModelClass.Users.User;
import com.server.ModelClass.Vehicle;
import com.server.RowMappers.VehicleRowMapper;
import com.server.Utility.UserRoleConstraints;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
                INSERT INTO vehicle(licensePlate,status,maxWeight,currentWeight)
                VALUES (?,?,?,?);
                 """;
        jdbcTemplate.update(
                sql,
                vehicle.getLicensePlate(),
                VehicleStatus.toInteger(vehicle.getStatus()),
                vehicle.getMaxWeight(),
                vehicle.getCurrentWeight()
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
                FROM vehicle natural join courier_vehicle natural join vehicle_address
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
                FROM  package natural join package_tag
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
                }
        );

    }
}
