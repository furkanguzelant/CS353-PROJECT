package com.server.DataAccessObject;

import com.server.Enums.PackageStatus;
import com.server.Enums.ProcessType;
import com.server.ModelClass.Package;
import com.server.ModelClass.Step;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PackageDataAccessService implements PackageDao {
    private final JdbcTemplate jdbcTemplate;

    public PackageDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertPackage(Package pack) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = """
                INSERT INTO package(weight, volume, status, senderAddressID, receiverAddressID, licensePlate, senderid, receiverid, storageid)
                VALUES (?,?,?,?,?,?,?,?,?);
                 """;

        jdbcTemplate.update(conn -> {

            // Pre-compiling SQL
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setInt(1, pack.getWeight());
            preparedStatement.setInt(2, pack.getVolume());
            preparedStatement.setInt(3, pack.getStatus().ordinal());
            preparedStatement.setInt(4, pack.getSenderAddressID());
            preparedStatement.setInt(5, pack.getReceiverAddressID());
            preparedStatement.setString(6, pack.getLicencePlate());
            preparedStatement.setInt(7, pack.getSenderID());
            preparedStatement.setInt(8, pack.getReceiverID());
            preparedStatement.setInt(9, pack.getStorageID());

            return preparedStatement;

        }, keyHolder);

        Integer id = (Integer) keyHolder.getKeys().get("packageid");
        System.out.println(id);

        String sql2 = """
                INSERT INTO package_tag(packageID, tag)
                VALUES (?,?);
                 """;

        for (int i = 0; i < pack.getTags().size(); i++) {
            jdbcTemplate.update(
                    sql2,
                    id,
                    pack.getTags().get(i)
            );
        }
        return id;
    }

    @Override
    public List<Package> selectAllPackages() {
        var sql = """
                SELECT *
                FROM package
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
                    resultSet.getInt("storageID"),
                    resultSet.getInt("courierID"));
        });
    }

    @Override
    public Optional<Package> getPackageById(int packageID) {
        var sql = """
                SELECT *
                FROM package
                WHERE packageID = ?
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
                }, packageID)
                .stream()
                .findFirst();
    }


    @Override
    public List<Step> getStepsOfPackage(int packageID) {
        var sql = """
                SELECT *
                FROM  step
                WHERE packageID = ?
                 """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Step(
                    resultSet.getInt("stepID"),
                    resultSet.getDate("receive_date"),
                    ProcessType.fromInteger(
                            resultSet.getInt("processType")
                    ),
                    resultSet.getInt("packageID"),
                    resultSet.getInt("prevAddress"),
                    resultSet.getInt("nextAddress")
            );
        }, packageID);
    }

    @Override
    public List<Package> getPackagesByCustomerId(int senderID) {

        var sql = """
                SELECT *
                FROM package
                WHERE senderid = ?
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
        }, senderID);
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

}