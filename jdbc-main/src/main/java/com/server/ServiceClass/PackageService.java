package com.server.ServiceClass;

import com.server.DTO.EmployeePackageDTO;
import com.server.DataAccessObject.PackageDao;
import com.server.ModelClass.Package;
import com.server.ModelClass.Step;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Package> getPackageById(int packageID){
        return packageDao.getPackageById(packageID);
    }

    public List<Step> getStepsOfPackage(int packageID){
        return packageDao.getStepsOfPackage(packageID);
    }

    public List<Package> getPackagesByCustomerId(int userID){
        return packageDao.getPackagesByCustomerId(userID);
    }

    public  List<EmployeePackageDTO> getPackagesInStorageByEmployeeID(int employeeID) {
        return packageDao.getPackagesInStorageByEmployeeID(employeeID);
    }
}
