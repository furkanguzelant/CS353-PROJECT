package com.server.DataAccessObject;

import com.server.ModelClass.Package;
import com.server.ModelClass.Step;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public class PackageDataAccessService implements PackageDao{
    private final JdbcTemplate jdbcTemplate;

    public VehicleDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    @Override
    public void insertPackage(Package user) {

    }

    @Override
    public  List<Package> selectAllPackages() {

    }

    @Override
    public Optional<Package> getPackageById(int packageID) {

    }

    @Override
    public  List<Step> getStepsOfPackage() {

    }
    */
}