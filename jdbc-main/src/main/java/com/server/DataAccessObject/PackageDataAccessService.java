package com.server.DataAccessObject;

import com.server.ModelClass.Package;
import com.server.ModelClass.Step;
import com.server.RowMappers.PackageRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.server.Enums.PackageStatus;
import com.server.Enums.ProcessType;

import java.util.List;
import java.util.Optional;
@Repository
public class PackageDataAccessService implements PackageDao{
    private final JdbcTemplate jdbcTemplate;

    public PackageDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void insertPackage(Package pack) {
        String sql = """
                INSERT INTO package(packageID, weight, volume, status, tags, senderAddressID, receiverAddressID, licencePlate, customerID, paymentID)
                VALUES (?,?,?,?,?,?,?,?,?,?);
                 """;

        jdbcTemplate.update(
                sql,
                pack.getPackageID(),
                pack.getWeight(),
                pack.getVolume(),
                pack.getStatus(),
                pack.getTags(),
                pack.getSenderAddressID(),
                pack.getReceiverAddressID(),
                pack.getLicencePlate(),
                pack.getCustomerID(),
                pack.getPaymentID()
        );
    }

    @Override
    public  List<Package> selectAllPackages() {
        var sql = """
                SELECT *
                FROM package
                 """;

        return jdbcTemplate.query(sql, new PackageRowMapper());
    }

    @Override
    public Optional<Package> getPackageById(int packageID) {
        var sql = """
                SELECT *
                FROM package
                WHERE packageID = ?
                 """;


        return jdbcTemplate.query(sql, new PackageRowMapper(), packageID)
                .stream()
                .findFirst();
    }


    @Override
    public List<Step> getStepsOfPackage() {
        var sql = """
                SELECT *
                FROM  step
                WHERE packageID = ?
                 """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Step(
                    resultSet.getInt("stepID"),
                    resultSet.getDate("receive_date"),
                    resultSet.getInt("volume"),
                    ProcessType.fromInteger(
                            resultSet.getInt("processType")
                    ),
                    resultSet.getInt("packageID"),
                    resultSet.getInt("prevAddress"),
                    resultSet.getInt("nextAddress")
            );
        });
    }

    @Override
    public List<Package> getPackagesByCustomerId(int userID){
        var sql = """
            SELECT *
            FROM  package
            WHERE customerID = ?
             """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Package(
                    resultSet.getInt("packageID"),
                    resultSet.getInt("weight"),
                    resultSet.getInt("volume"),
                    PackageStatus.fromInteger(
                            resultSet.getInt("status")
                    ),
                    resultSet.getSet("tags"),
                    resultSet.getInt("senderAddressID"),
                    resultSet.getInt("receiverAddressID"),
                    resultSet.getInt("licencePlate"),
                    resultSet.getInt("customerID"),
                    resultSet.getInt("paymentID")
            );
        });
    }
}