package com.server.DataAccessObject;

import com.server.DTO.EmployeePackageDTO;
import com.server.ModelClass.Package;
import com.server.ModelClass.Step;

import java.util.List;
import java.util.Optional;

public interface PackageDao {

    int insertPackage(Package pack);

    List<Package> selectAllPackages();

    Package getPackageById(int packageID);

    List<Step> getStepsOfPackage(int packageID);

    List<Package> getPackagesByCustomerId(int userID);
    List<EmployeePackageDTO> getPackagesInStorageByEmployeeID(int employeeID);
    void assignPackageToCourier(int packageID, int courierID);
    void updatePackageStatus(int packageID, int packageStatus);

}
