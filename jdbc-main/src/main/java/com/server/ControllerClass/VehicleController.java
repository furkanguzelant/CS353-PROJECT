package com.server.ControllerClass;

import com.server.ModelClass.LogisticUnits.LogisticUnit;
import com.server.ModelClass.Package;
import com.server.ModelClass.Users.User;
import com.server.ModelClass.Vehicle;
import com.server.ServiceClass.LogisticUnitService;
import com.server.ServiceClass.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping(path = "api/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    private final LogisticUnitService logisticUnitService;


    public VehicleController(VehicleService vehicleService, LogisticUnitService logisticUnitService) {
        this.vehicleService = vehicleService;
        this.logisticUnitService = logisticUnitService;
    }

    @PostMapping(path="insertVehicle")
    public ResponseEntity<Map<String, Object>> insertVehicle(@RequestBody Vehicle vehicle, @RequestParam int employeeID) {

        try {
            LogisticUnit logisticUnit = logisticUnitService.getLogisticUnitByEmployeeID(employeeID);
            vehicle.setAddressID(logisticUnit.getAddressID());
            vehicleService.insertVehicle(vehicle);
            return new ResponseEntity<>(Map.of("statusMessage", "Vehicle with license plate " + vehicle.getLicensePlate() + " created"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Vehicle couldn't be created"), HttpStatus.BAD_REQUEST);
        }
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
    @GetMapping(path = "getPackagesOfCourierInsideVehicle")
    public List<Package> getPackagesOfCourierInsideVehicle(int courierID){
        return vehicleService.getPackagesOfCourierInsideVehicle(courierID);
    }

}
