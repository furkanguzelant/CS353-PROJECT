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
    public List<Package> getPackagesOfVehicle(String licensePlate) {

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
                    resultSet.getString("licensePlate"),
                    resultSet.getInt("senderID"),
                    resultSet.getInt("receiverID"),
                    resultSet.getInt(("storageID")),
                    resultSet.getInt("courierID"));
        }, licensePlate);

    }

    @Override
    public void addPackageToVehicle(int packageID, String licensePlate) {
        String sql = """
                 UPDATE package
                 SET licensePlate = ?, status = ?
                 WHERE packageID = ?
                 """;

        jdbcTemplate.update(
                sql,
                licensePlate, VehicleStatus.Busy,
                packageID
        );

    }

    @Override
    public Vehicle getVehicleFromCourierID(int courierID) {

        var sql = """
                SELECT *
                FROM  vehicle
                WHERE courierID = ?
                 """;

        return jdbcTemplate.queryForObject(sql, new VehicleRowMapper(), courierID);
    }

    @Override
    public void assignVehicleToCourier(String licensePlate, int courierID) {
        String sql = """
                 UPDATE vehicle
                 SET courierID = NULL
                 WHERE courierID = ? AND currentWeight = 0
                 """;

        jdbcTemplate.update(
                sql,
                courierID
        );

        String sql2 = """
                 UPDATE vehicle
                 SET courierID = ?
                 WHERE licensePlate = ? AND currentWeight = 0
                 """;

        jdbcTemplate.update(
                sql2,
                courierID,
                licensePlate
        );
    }

    @Override
    public List<Package> getPackagesOfCourierInsideVehicle(int courierID) {
        var sql = """
                SELECT *
                FROM  vehicle natural join package
                WHERE courierid = ?
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
                    resultSet.getString("licensePlate"),
                    resultSet.getInt("senderID"),
                    resultSet.getInt("receiverID"),
                    resultSet.getInt(("storageID")),
                    courierID);
        }, courierID);
    }

    public List<String> getTagsOfPackage(int packageID) {
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

    public List<Vehicle> getVehiclesOfLogisticUnit(int logisticUnitID) {
        var sql = """
                SELECT *
                FROM  vehicle natural join logisticUnit
                WHERE logisticUnitID = ?
                 """;

        return jdbcTemplate.query(sql, new VehicleRowMapper(), logisticUnitID);
    }

    public void loadPackageToVehicle(int packageID, String licensePlate) {

        var sql = """
                UPDATE package
                SET licensePlate = ?, storageID = NULL
                WHERE packageID = ?
                 """;

        jdbcTemplate.update(sql, licensePlate, packageID);

        var sql2 = """
                UPDATE vehicle
                SET currentWeight = currentWeight + (SELECT weight FROM package WHERE packageID = ?), status = ?
                WHERE licensePlate = ?
                 """;

        jdbcTemplate.update(sql2, packageID, VehicleStatus.Busy.ordinal(), licensePlate);
    }

    public void removePackageFromVehicle(int packageID) {

        var sql2 = """
                UPDATE vehicle
                SET currentWeight = currentWeight - (SELECT weight FROM package WHERE packageID = ?)
                WHERE licensePlate = (SELECT licensePlate FROM package WHERE packageID = ?)
                 """;

        jdbcTemplate.update(sql2, packageID, packageID);

        var sql3 = """
                UPDATE vehicle
                SET status = ? 
                WHERE licensePlate = (SELECT licensePlate FROM package WHERE packageID = ?)
                AND currentWeight = 0
                 """;

        jdbcTemplate.update(sql3, VehicleStatus.Available.ordinal(), packageID);

        var sql = """
                UPDATE package
                SET licensePlate = NULL
                WHERE packageID = ?
                 """;
        jdbcTemplate.update(sql, packageID);


    }
}
