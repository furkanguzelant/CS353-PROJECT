package com.server.ControllerClass;

import com.server.ModelClass.Package;
import com.server.ModelClass.Users.User;
import com.server.ModelClass.Vehicle;
import com.server.ServiceClass.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping(path = "api/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;


    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(path="insertVehicle")
    public void insertVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.insertVehicle(vehicle);
    }

    @GetMapping(path="getVehicles")
    public List<Vehicle> selectAllVehicles(){
        return vehicleService.selectAllVehicles();
    }

    @GetMapping(path = "getVehicleByLicensePlate")
    public Optional<Vehicle> getVehicleByLicensePlate(String licensePlate){
        return vehicleService.getVehicleByLicensePlate(licensePlate);
    }
    @GetMapping(path ="getPackagesOfVehicle" )
    public List<Package> getPackagesOfVehicle(String licensePlate){
        return vehicleService.getPackagesOfVehicle(licensePlate);
    }

    @GetMapping(path ="addPackageToVehicle" )
    void addPackageToVehicle(int packageID, String licensePlate){
        vehicleService.addPackageToVehicle(packageID,licensePlate);
    }

    @GetMapping(path ="getVehicleFromCourierID" )
    Optional<Vehicle> getVehicleFromCourierID(int courierID){
        return vehicleService.getVehicleFromCourierID(courierID);
    }
    @GetMapping(path ="assignVehicleToCourier" )
    public void assignVehicleToCourier(String licensePlate, int courierID){
        vehicleService.assignVehicleToCourier(licensePlate,courierID);
    }

}
