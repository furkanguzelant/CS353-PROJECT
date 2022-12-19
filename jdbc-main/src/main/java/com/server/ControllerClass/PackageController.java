package com.server.ControllerClass;

import com.server.ModelClass.Package;
import com.server.ServiceClass.PackageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping(path = "api/package")
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping(path="insertPackage")
    public void insertPackage(@RequestBody Package pack) {
        packageService.insertPackage(pack);
    }

    @GetMapping(path="getPackages")
    public List<Package> selectAllPackages(){
        return packageService.selectAllPackages();
    }

    @GetMapping(path = "getPackagesByCustomerId")
    public Optional<Package> getPackagesByCustomerId(int customerID){
        return packageService.getPackagesByCustomerId(customerID);
    }

}
