package com.server.RowMappers;

import com.server.DTO.CourierPackageDTO;
import com.server.DTO.PackageDTO;
import com.server.Enums.PackageStatus;
import com.server.Enums.PaymentStatus;
import com.server.Enums.PaymentType;
import com.server.ModelClass.Address;
import com.server.ModelClass.Package;
import com.server.ModelClass.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierPackageRowMapper implements RowMapper<CourierPackageDTO> {


    public CourierPackageDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        return new CourierPackageDTO(
                new Package(
                        resultSet.getInt("packageID"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("volume"),
                        PackageStatus.fromInteger(
                                resultSet.getInt("status")
                        ),
                        null,
                        resultSet.getInt("senderAddressID"),
                        resultSet.getInt("receiverAddressID"),
                        resultSet.getString("licensePlate"),
                        resultSet.getInt("senderID"),
                        resultSet.getInt("receiverID"),
                        resultSet.getInt("storageID"),
                        resultSet.getInt("courierID")
                ),
                new Address(
                        resultSet.getInt("addressID"),
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("district"),
                        resultSet.getString("zipcode"),
                        resultSet.getString("addressInfo")
                )
        );
    }
}
