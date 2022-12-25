package com.server.DataAccessObject;

import com.server.ModelClass.Package;
import com.server.ModelClass.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleDao {

    void insertVehicle(Vehicle vehicle);

    List<Vehicle> selectAllVehicles();

    Optional<Vehicle> getVehicleByLicensePlate(String licensePlate);

    List<Package> getPackagesOfVehicle(String licensePlate);

    void addPackageToVehicle(int packageID, String licensePlate);

    Vehicle getVehicleFromCourierID(int courierID);

    void assignVehicleToCourier(String licensePlate, int courierID);

    List<Package> getPackagesOfCourierInsideVehicle(int courierID);

    List<Vehicle> getVehiclesOfLogisticUnit(int logisticUnitID);

    void loadPackageToVehicle(int packageID, String licensePlate);



}
