package com.server.ServiceClass;

import com.server.DTO.PackageDTO;
import com.server.DataAccessObject.PackageDao;
import com.server.ModelClass.Package;
import com.server.ModelClass.Step;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    private final PackageDao packageDao;

    public PackageService(PackageDao packageDao) {
        this.packageDao = packageDao;
    }

    public int insertPackage(Package pack){
        return packageDao.insertPackage(pack);
    }

    public List<Package> selectAllPackages(){
        return packageDao.selectAllPackages();
    }

    public Package getPackageById(int packageID){
        return packageDao.getPackageById(packageID);
    }

    public List<Step> getStepsOfPackage(int packageID){
        return packageDao.getStepsOfPackage(packageID);
    }

    public List<Package> getPackagesByCustomerId(int userID){
        return packageDao.getPackagesByCustomerId(userID);
    }

    public  List<PackageDTO> getPackagesInStorageByEmployeeID(int employeeID) {
        return packageDao.getPackagesInStorageByEmployeeID(employeeID);
    }

    public void assignPackageToCourier(int packageID, int courierID) {
        packageDao.assignPackageToCourier(packageID, courierID);
    }

    public void  updatePackageStatus(int packageID, int packageStatus) {
        packageDao.updatePackageStatus(packageID, packageStatus);
    }

    public List<PackageDTO> getIncomingPackagesOfCustomer(int customerID) {
        return packageDao.getIncomingPackagesOfCustomer( customerID);
    }

    public List<PackageDTO> getSentPackagesOfCustomer(int customerID) {
        return packageDao.getSentPackagesOfCustomer( customerID);
    }

    public List<PackageDTO> getPackagesFilterByWeight(int upperWeightLimit, int lowerWeightLimit) {
        return packageDao.getPackagesFilterByWeight(upperWeightLimit,lowerWeightLimit);
    }

    public List<PackageDTO> getPackagesFilterByCity(String inputString) {
        return packageDao.getPackagesFilterByCity(inputString);
    }
}
