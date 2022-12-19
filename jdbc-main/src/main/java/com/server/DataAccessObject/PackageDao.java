package com.server.DataAccessObject;

import com.server.ModelClass.Package;
import com.server.ModelClass.Step;

import java.util.List;
import java.util.Optional;

public interface PackageDao {

    void insertPackage(Package pack);

    List<Package> selectAllPackages();

    Optional<Package> getPackageById(int packageID);

    List<Step> getStepsOfPackage();

    List<Package> getPackagesByCustomerId(int customerID);

}
