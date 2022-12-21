package com.server.ControllerClass;

import com.server.ModelClass.Package;
import com.server.ModelClass.Step;
import com.server.ServiceClass.PackageService;

import java.util.List;
import java.util.Optional;

public class PackageController {

        private final PackageService packageService;

        public PackageController(PackageService packageService) {
         this.packageService = packageService;
         }


    void insertPackage(Package pack){
            packageService.insertPackage(pack);
        }

        List<Package> selectAllPackages(){
            return packageService.selectAllPackages();
        }

        Optional<Package> getPackageById(int packageID){
            return packageService.getPackageById(packageID);
        }

        List<Step> getStepsOfPackage(int packageID){
            return packageService.getStepsOfPackage(packageID);
        }

        List<Package> getPackagesByCustomerId(int userID){
            return packageService.getPackagesByCustomerId(userID);
        }
}
