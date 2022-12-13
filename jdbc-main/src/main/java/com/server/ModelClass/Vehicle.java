package com.server.ModelClass;

public class Vehicle {

    private String licensePlate;
    enum Status{
        Busy,
        Waiting
    }
    private Status status;
    private int maxWeight;
    private int currentWeight;

    public Vehicle(){

    }
    private Integer courierID;

    private Integer addressID;

    //Constructor
    public Vehicle(String licensePlate, Status status, int maxWeight, Integer courierID, Integer addressID ) {
        this.licensePlate = licensePlate;
        this.status = status;
        this.maxWeight = maxWeight;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
