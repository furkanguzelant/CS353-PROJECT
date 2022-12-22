package com.server.ControllerClass;

import com.server.ModelClass.Package;
import com.server.ModelClass.Step;
import com.server.ModelClass.Storage;
import com.server.ServiceClass.PackageService;
import com.server.ServiceClass.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/package")
public class PackageController {

        private final PackageService packageService;
        private final StorageService storageService;

        public PackageController(PackageService packageService,StorageService storageService) {
         this.packageService = packageService;
         this.storageService = storageService;
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
