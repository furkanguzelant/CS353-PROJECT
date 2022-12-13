package com.server.RowMappers;

import com.server.Enums.VehicleStatus;
import com.server.ModelClass.Vehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRowMapper implements RowMapper<Vehicle> {
    @Override
    public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Vehicle(
                resultSet.getString("licensePlate"),

                VehicleStatus.fromInteger(
                        resultSet.getInt("status")
                ),
                resultSet.getInt("maxWeight"),
                resultSet.getInt("currentWeight")
        );
    }
}
