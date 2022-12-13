package com.server.ControllerClass;

import com.server.ModelClass.Users.User;
import com.server.ModelClass.Vehicle;
import com.server.ServiceClass.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

}
