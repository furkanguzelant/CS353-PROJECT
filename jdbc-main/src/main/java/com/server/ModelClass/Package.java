package com.server.ModelClass;

import com.server.Enums.PackageStatus;

import java.util.List;

public class Package {

    public Package() {
    }


    private int packageID;
    private int weight;
    private int volume;
    private PackageStatus status;
    private List<String> tags;
    private int senderAddressID;
    private int receiverAddressID;
    private String licencePlate;
    private int senderID;
    private int receiverID;
    private int storageID;
    private int courierID;


    public Package(int packageID,
                   int weight,
                   int volume,
                   PackageStatus status,
                   List<String> tags,
                   int senderAddressID,
                   int receiverAddressID,
                   String licencePlate,
                   int senderID,
                   int receiverID,
                   int storageID,
                   int courierID) {
        this.packageID = packageID;
        this.weight = weight;
        this.volume = volume;
        this.status = status;
        this.tags = tags;
        this.senderAddressID = senderAddressID;
        this.receiverAddressID = receiverAddressID;
        this.licencePlate = licencePlate;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.storageID = storageID;
        this.courierID = courierID;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public PackageStatus getStatus() {
        return status;
    }

    public void setStatus(PackageStatus status) {
        this.status = status;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getSenderAddressID() {
        return senderAddressID;
    }

    public void setSenderAddressID(int senderAddressID) {
        this.senderAddressID = senderAddressID;
    }

    public int getReceiverAddressID() {
        return receiverAddressID;
    }

    public void setReceiverAddressID(int receiverAddressID) {
        this.receiverAddressID = receiverAddressID;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public int getStorageID() {
        return storageID;
    }

    public void setStorageID(int storageID) {
        this.storageID = storageID;
    }

    public int getCourierID() {
        return courierID;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }
}
