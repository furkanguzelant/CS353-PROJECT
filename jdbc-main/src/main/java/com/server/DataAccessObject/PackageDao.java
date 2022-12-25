package com.server.DataAccessObject;

import com.server.DTO.CourierPackageDTO;
import com.server.DTO.PackageDTO;
import com.server.DTO.PackageStatisticsInfo;
import com.server.ModelClass.Package;
import com.server.ModelClass.Step;

import java.util.List;

public interface PackageDao {

    int insertPackage(Package pack);

    List<Package> selectAllPackages();

    Package getPackageById(int packageID);

    List<Step> getStepsOfPackage(int packageID);

    List<Package> getPackagesByCustomerId(int userID);
    List<PackageDTO> getPackagesInStorageByEmployeeID(int employeeID);
    void assignPackageToCourier(int packageID, int courierID);
    void updatePackageStatus(int packageID, int packageStatus);

    List<PackageDTO> getIncomingPackagesOfCustomer(int customerID);

    List<PackageDTO> getSentPackagesOfCustomer(int customerID);

    List<PackageDTO> getPackagesFilterByWeight(int upperWeightLimit, int lowerWeightLimit);

    List<PackageDTO> getPackagesFilterByCity(String inputString);
    PackageStatisticsInfo getPackageStatistics();
    List<CourierPackageDTO> getPackagesOfCourier(int courierID);
    List<CourierPackageDTO> getPackagesInVehicleOfCourier(int courierID);
}
