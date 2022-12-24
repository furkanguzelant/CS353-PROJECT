package com.server.ControllerClass;

import com.server.DTO.PackageStatisticsInfo;
import com.server.ModelClass.Package;
import com.server.ModelClass.Storage;
import com.server.ServiceClass.PackageService;
import com.server.ServiceClass.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "api/storage")
public class StorageController {

    private final StorageService storageService;
    private final PackageService packageService;

    public StorageController(StorageService storageService, PackageService packageService) {
        this.storageService = storageService;
        this.packageService = packageService;
    }

    @GetMapping(path="getStoragesByCourierID")
    public List<Storage> getStoragesByCourierID(int courierID) {
        return storageService.getStoragesByCourierID(courierID);
    }

    @GetMapping(path="getStoragesByEmployeeID")
    public ResponseEntity<Map<String, Object>> getStoragesByEmployeeID (@RequestParam int employeeID) {
        try {
            List<Storage> storages = storageService.getStoragesByEmployeeID(employeeID);
            System.out.println(storages);
                return new ResponseEntity<>(Map.of("statusMessage", "Storages of employees succesfully fetched" , "Storages", storages ), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "An exception occured"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path="insertPackageToStorage")
    public ResponseEntity<Map<String, Object>> insertPackageToStorage (@RequestParam int packageID, @RequestParam int storageID) {
        try {
            Package p = packageService.getPackageById(packageID);
            if ( p.getStorageID() == storageID ){
                throw new IllegalStateException("The package is already added to the storage");
            }

            storageService.insertPackageToStorage(packageID,storageID);
            return new ResponseEntity<>(Map.of("statusMessage", "Package has been inserted to the storage successfully"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
