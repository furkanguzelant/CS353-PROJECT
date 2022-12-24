package com.server.RowMappers;

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

public class EmployeePackageDTORowMapper implements RowMapper<PackageDTO> {


    @Override
    public PackageDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        return new PackageDTO(
                new Package(resultSet.getInt("packageID"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("volume"),
                        PackageStatus.fromInteger(
                                resultSet.getInt("package_status")
                        ),
                        null,
                        resultSet.getInt("senderAddressID"),
                        resultSet.getInt("receiverAddressID"),
                        resultSet.getString("licensePlate"),
                        resultSet.getInt("senderID"),
                        resultSet.getInt("receiverID"),
                        resultSet.getInt(("storageID")),
                        resultSet.getInt("courierID")),
                new Payment(
                        resultSet.getInt("price"),
                        PaymentType.fromInteger(resultSet.getInt("type")),
                        PaymentStatus.fromInteger(resultSet.getInt("payment_status")),
                        resultSet.getInt("packageid")
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
