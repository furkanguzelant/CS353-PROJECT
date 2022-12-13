package com.server.DataAccessObject;

import com.server.ModelClass.Package;
import com.server.ModelClass.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleDao {

    void insertVehicle(Vehicle vehicle);

    List<Vehicle> selectAllVehicles();

    Optional<Vehicle> getVehicleByLicensePlate(String licensePlate);

    List<Package> getPackagesOfVehicle();




}
