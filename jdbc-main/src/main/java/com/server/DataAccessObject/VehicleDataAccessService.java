package com.server.DataAccessObject;

import com.server.ModelClass.Users.User;
import com.server.ModelClass.Vehicle;
import com.server.RowMappers.VehicleRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
                vehicle.getStatus(),
                vehicle.getMaxWeight(),
                vehicle.getCurrentWeight()
        );
    }

    @Override
    public List<Vehicle> selectAllVehicles() {
        var sql = """
                SELECT *
                FROM vehicle natural join courier_vehicle natural join vehicle_address
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


       return jdbcTemplate.query(sql, new VehicleRowMapper(), licensePlate )
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

        return jdbcTemplate.query(sql, new PackageRowMapper());

    }
}
