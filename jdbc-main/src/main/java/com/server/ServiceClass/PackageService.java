package com.server.ServiceClass;


import com.server.DataAccessObject.PackageDao;
import com.server.ModelClass.Package;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {

    private final PackageDao packageDao;

    public PackageService(PackageDao packageDao) {
        this.packageDao = packageDao;
    }

    public void insertPackage(Package pack){
        packageDao.insertPackage(pack);
    }

    public List<Package> selectAllPackages(){
        return packageDao.selectAllPackages();
    }

    public Optional<Package> getPackageById(int packageID){
        return packageDao.getPackageById(packageID);
    }

    public List<Package> getPackagesByCustomerId(int customerID){
        return packageDao.getPackagesByCustomerId(customerID);
    }
}