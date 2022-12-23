package com.server.RowMappers;

import com.server.Enums.ComplaintType;
import com.server.ModelClass.Complaint;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplaintRowMapper implements  RowMapper<Complaint>{
    @Override
    public Complaint mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Complaint(
                resultSet.getInt("complaintID"),
                resultSet.getInt("packageID"),
                resultSet.getInt("registeredCustomerID"),
                ComplaintType.fromInteger(
                        resultSet.getInt("typeOfComplaint")
                ),
                resultSet.getString("message")
        );
    }
}
