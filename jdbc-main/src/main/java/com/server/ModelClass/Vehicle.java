package com.server.ModelClass;

import com.server.Enums.VehicleStatus;

public class Vehicle {

    private String licensePlate;

    private VehicleStatus status;
    private int maxWeight;
    private int currentWeight;

    public Vehicle(){

    }
    private Integer courierID;

    private Integer addressID;

    //Constructor
    public Vehicle(String licensePlate, VehicleStatus status, int maxWeight, int currentWeight, Integer courierID, Integer addressID ) {
        this.licensePlate = licensePlate;
        this.status = status;
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.courierID = courierID;
        this.addressID = addressID;
    }

    //Getter - Setters

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Integer getCourierID() {
        return courierID;
    }

    public void setCourierID(Integer courierID) {
        this.courierID = courierID;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }
}
