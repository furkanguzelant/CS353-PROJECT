package com.server.ServiceClass;


import com.server.DataAccessObject.VehicleDao;
import com.server.ModelClass.Package;
import com.server.ModelClass.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleDao vehicleDao;


    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public void insertVehicle(Vehicle vehicle){
        vehicleDao.insertVehicle(vehicle);
    }

    public List<Vehicle> selectAllVehicles(){
        return vehicleDao.selectAllVehicles();
    }

    public Optional<Vehicle> getVehicleByLicensePlate(String licensePlate){
        return vehicleDao.getVehicleByLicensePlate(licensePlate);
    }

    public List<Package> getPackagesOfVehicle(String licensePlate){
        return vehicleDao.getPackagesOfVehicle(licensePlate);
    }

    public Optional<Vehicle> getVehicleFromCourierID(int courierID){
        return vehicleDao.getVehicleFromCourierID(courierID);
    }

    public List<Package> getPackagesOfCourierInsideVehicle(int courierID){
        return vehicleDao.getPackagesOfCourierInsideVehicle(courierID);
    }
    public void assignVehicleToCourier(String licensePlate, int courierID){
         vehicleDao.assignVehicleToCourier(licensePlate,courierID);
    }

    public void addPackageToVehicle(int packageID, String licensePlate){
        vehicleDao.addPackageToVehicle(packageID,licensePlate);
    }

    public List<Vehicle> getVehiclesOfLogisticUnit(int logisticUnitID) {
        return vehicleDao.getVehiclesOfLogisticUnit(logisticUnitID);
    }



}
