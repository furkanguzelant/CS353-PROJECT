package com.server.DataAccessObject;

import com.server.DTO.CourierPackageDTO;
import com.server.DTO.PackageDTO;
import com.server.DTO.PackageStatisticsInfo;
import com.server.Enums.*;
import com.server.ModelClass.Address;
import com.server.ModelClass.Package;
import com.server.ModelClass.Payment;
import com.server.ModelClass.Step;
import com.server.RowMappers.CourierPackageRowMapper;
import com.server.RowMappers.EmployeePackageDTORowMapper;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public Package getPackageById(int packageID) {
        var sql = """
                SELECT *
                FROM package
                WHERE packageID = ?
                 """;


        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
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
        }, packageID);

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

    @Override
    public List<PackageDTO> getPackagesInStorageByEmployeeID(int employeeID) {
        var sql = """
                SELECT package.packageID, weight, volume, package.status as package_status,
                 senderAddressID, receiverAddressID, licensePlate, senderID, receiverID, courierID,
                  storageID, price, type, payment.status as payment_status, addressid, country, city,
                 district, zipcode, addressinfo
                FROM employee natural join storage natural join package, payment, address
                WHERE employee.userID = ?
                AND package.packageID = payment.packageID 
                AND address.addressID = package.receiverAddressID
                AND package.packageID not in (SELECT packageid FROM package WHERE courierid IS NOT NULL )
                 """;

        List<PackageDTO> packageDTOList = jdbcTemplate.query(sql, new EmployeePackageDTORowMapper(), employeeID);

        for (int i = 0; i < packageDTOList.size(); i++) {
            int packageID = packageDTOList.get(i).getPack().getPackageID();
            List<String> tags = getTagsOfPackage(packageID);
            packageDTOList.get(i).getPack().setTags(tags);
        }
        return packageDTOList;
    }

    public void assignPackageToCourier(int packageID, int courierID) {
        var sql = """
                UPDATE package
                SET courierID = ?, status = ?
                WHERE packageID = ?
                 """;

        jdbcTemplate.update(sql, courierID, PackageStatus.InDistribution.ordinal(), packageID);

        var sql2 = """
                UPDATE courier
                SET status = ?
                WHERE userID = ?
                 """;

        jdbcTemplate.update(sql2, CourierStatus.IN_DISTRIBUTION.ordinal(), courierID);

    }

    public void updatePackageStatus(int packageID, int packageStatus) {
        String sql = """
                UPDATE package
                SET status = ?
                WHERE packageID = ?
                 """;
        jdbcTemplate.update(
                sql,
                packageStatus, packageID
        );
    }

    @Override
    public List<PackageDTO> getIncomingPackagesOfCustomer(int customerID) {
        var sql = """
                SELECT package.packageID, weight, volume, package.status as package_status,
                 senderAddressID, receiverAddressID, licensePlate, senderID, receiverID, courierID,
                  storageID, price, type, payment.status as payment_status, addressid, country, city,
                 district, zipcode, addressinfo
                FROM package, payment, address
                WHERE package.receiverid = ?
                AND package.packageID = payment.packageID 
                AND address.addressID = package.receiverAddressID
                 """;

        List<PackageDTO> packageDTOList = jdbcTemplate.query(sql, new EmployeePackageDTORowMapper(), customerID);

        for (int i = 0; i < packageDTOList.size(); i++) {
            int packageID = packageDTOList.get(i).getPack().getPackageID();
            List<String> tags = getTagsOfPackage(packageID);
            packageDTOList.get(i).getPack().setTags(tags);
        }
        return packageDTOList;
    }

    @Override
    public List<PackageDTO> getSentPackagesOfCustomer(int customerID) {
        var sql = """
                SELECT package.packageID, weight, volume, package.status as package_status,
                 senderAddressID, receiverAddressID, licensePlate, senderID, receiverID, courierID,
                  storageID, price, type, payment.status as payment_status, addressid, country, city,
                 district, zipcode, addressinfo
                FROM package, payment, address
                WHERE package.senderid = ?
                AND package.packageID = payment.packageID 
                AND address.addressID = package.receiverAddressID
                 """;

        List<PackageDTO> packageDTOList = jdbcTemplate.query(sql, new EmployeePackageDTORowMapper(), customerID);

        for (int i = 0; i < packageDTOList.size(); i++) {
            int packageID = packageDTOList.get(i).getPack().getPackageID();
            List<String> tags = getTagsOfPackage(packageID);
            packageDTOList.get(i).getPack().setTags(tags);
        }
        return packageDTOList;
    }

    @Override
    public List<PackageDTO> getPackagesFilterByWeight(int upperWeightLimit, int lowerWeightLimit) {
        var sql = """
                SELECT package.packageID, weight, volume, package.status as package_status,
                 senderAddressID, receiverAddressID, licensePlate, senderID, receiverID, courierID,
                  storageID, price, type, payment.status as payment_status, addressid, country, city,
                 district, zipcode, addressinfo
                FROM package, payment, address
                WHERE weight between ? and ?
                AND package.packageID = payment.packageID 
                AND address.addressID = package.receiverAddressID
                 """;

        List<PackageDTO> packageDTOList = jdbcTemplate.query(sql, new EmployeePackageDTORowMapper(), lowerWeightLimit, upperWeightLimit);

        for (int i = 0; i < packageDTOList.size(); i++) {
            int packageID = packageDTOList.get(i).getPack().getPackageID();
            List<String> tags = getTagsOfPackage(packageID);
            packageDTOList.get(i).getPack().setTags(tags);
        }
        return packageDTOList;
    }

    @Override
    public List<PackageDTO> getPackagesFilterByCity(String inputString) {

        inputString = "%" + inputString + "%";

        var sql = """
                SELECT package.packageID, weight, volume, package.status as package_status,
                senderAddressID, receiverAddressID, licensePlate, senderID, receiverID, courierID,
                storageID, price, type, payment.status as payment_status, addressid, country, city,
                district, zipcode, addressinfo
                FROM package, payment, address
                WHERE (LOWER(address.city) like LOWER(?))
                AND package.packageID = payment.packageID 
                AND address.addressID = package.receiverAddressID
                """;

        List<PackageDTO> packageDTOList = jdbcTemplate.query(sql, new EmployeePackageDTORowMapper(), inputString);

        for (int i = 0; i < packageDTOList.size(); i++) {
            int packageID = packageDTOList.get(i).getPack().getPackageID();
            List<String> tags = getTagsOfPackage(packageID);
            packageDTOList.get(i).getPack().setTags(tags);
        }
        return packageDTOList;
    }

    public PackageStatisticsInfo getPackageStatistics() {
        PackageStatisticsInfo statisticsInfo = new PackageStatisticsInfo();
        var sql = """
                WITH city_number(city, packageNo) AS
                    (SELECT city, count(*)
                    FROM package, address
                    WHERE package.receiverAddressID = addressID
                    GROUP BY city)
                SELECT city
                FROM city_number
                WHERE packageNo = (SELECT max(packageNo) FROM city_number); 
                 """;

        List<String> cities = jdbcTemplate.queryForList(sql, String.class);
        String city = cities.get(0);

        var sql5 = """
                WITH logisticUnit_packageNo(logisticUnitName, packageNo) AS 
                    (SELECT logisticunit.name, count(*)
                    FROM logisticunit natural join storage natural join package
                     GROUP BY logisticunit.name)
                SELECT logisticUnitName
                FROM logisticUnit_packageNo
                WHERE packageNo = (SELECT max(packageNo) FROM logisticUnit_packageNo); 
                 """;

        String logisticUnit = jdbcTemplate.queryForObject(sql5, String.class);


        var sql2 = """
                SELECT count(*)
                FROM package
                 """;

        Integer totalPackages = jdbcTemplate.queryForObject(sql2, Integer.class);

        var sql3 = """
                SELECT count(*)
                FROM package
                WHERE status = '1';
                 """;

        Integer inDistribution = jdbcTemplate.queryForObject(sql3, Integer.class);

        var sql4 = """
                SELECT count(*)
                FROM package
                WHERE status = '2';
                 """;

        Integer inBranch = jdbcTemplate.queryForObject(sql4, Integer.class);


        statisticsInfo.setMaxPackageCity(city);
        statisticsInfo.setMaxLogisticUnit(logisticUnit);
        statisticsInfo.setTotalPackages(totalPackages);
        statisticsInfo.setNumberOfPackageInDistribution(inDistribution);
        statisticsInfo.setNumberOfPackageInBranch(inBranch);
        return statisticsInfo;
    }

    public List<CourierPackageDTO> getPackagesOfCourier(int courierID) {
        var sql = """
                SELECT packageID
                FROM package
                WHERE courierID = ?
                AND storageid is not NULL;
                 """;

        List<Integer> packageIDList = jdbcTemplate.queryForList(sql, Integer.class, courierID);

        var sql2 = """
                SELECT *
                FROM step natural join package, address
                WHERE nextAddressId not in (select prevAddressId from step WHERE packageID = ?)
                  AND step.nextAddressID = address.addressID
                  AND nextaddressid is not NULL
                  AND courierID = ?
                  AND packageid = ?
                  AND storageid is not NULL;
                 """;

        List<CourierPackageDTO> packageDTOList = new ArrayList<>();
        for(int i = 0; i < packageIDList.size(); i++) {
            CourierPackageDTO pack = jdbcTemplate.queryForObject(sql2,
                    new CourierPackageRowMapper(),
                    packageIDList.get(i),
                    courierID, packageIDList.get(i));
            packageDTOList.add(pack);
        }

        for (int i = 0; i < packageDTOList.size(); i++) {
            int packageID = packageDTOList.get(i).getPack().getPackageID();
            List<String> tags = getTagsOfPackage(packageID);
            packageDTOList.get(i).getPack().setTags(tags);
        }

        return packageDTOList;
    }

    public List<CourierPackageDTO> getPackagesInVehicleOfCourier(int courierID) {
        var sql = """
                SELECT packageID
                FROM package
                WHERE package.courierID = ?
                AND package.licenseplate is not null
                 """;

        List<Integer> packageIDList = jdbcTemplate.queryForList(sql, Integer.class, courierID);

        var sql2 = """
                SELECT *
                FROM step natural join package, address
                WHERE nextAddressId not in (select prevAddressId from step WHERE packageID = ?)
                  AND step.nextAddressID = address.addressID
                  AND packageid = ?;
                 """;

        List<CourierPackageDTO> packageDTOList = new ArrayList<>();
        for(int i = 0; i < packageIDList.size(); i++) {
            CourierPackageDTO pack = jdbcTemplate.queryForObject(sql2,
                    new CourierPackageRowMapper(),
                    packageIDList.get(i), packageIDList.get(i));
            packageDTOList.add(pack);
        }

        for (int i = 0; i < packageDTOList.size(); i++) {
            int packageID = packageDTOList.get(i).getPack().getPackageID();
            List<String> tags = getTagsOfPackage(packageID);
            packageDTOList.get(i).getPack().setTags(tags);
        }

        return packageDTOList;
    }
}














