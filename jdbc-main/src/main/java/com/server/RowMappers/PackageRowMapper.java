package com.server.RowMappers;

import com.server.Enums.PackageStatus;
import com.server.ModelClass.Package;
import com.server.ModelClass.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageRowMapper implements RowMapper<Package> {
    @Override
    public Package mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Package(
                resultSet.getInt("packageID"),
                resultSet.getInt("weight"),
                resultSet.getInt("volume"),
                PackageStatus.fromInteger(
                        resultSet.getInt("status")
                ),
//                resultSet.getList("tags"),
                null,
                resultSet.getInt("senderAddressID"),
                resultSet.getInt("receiverAddressID"),
                resultSet.getInt("licencePlate"),
                resultSet.getInt("customerID"),
                resultSet.getInt("paymentID")
        );
    }
}
