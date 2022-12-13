package com.server.ModelClass;

import java.util.List;

public class Package {

    public Package() {
    }

    enum Status {Delivered, InDistribution, InBranch}

    private int packageID;
    private int weight;
    private int volume;
    private Status status;
    private List<String> tags;
    private int senderAddressID;
    private int receiverAddressID;
    private int licencePlate;
    private int customerID;
    private int paymentID;

    public Package(int packageID,
                   int weight,
                   int volume,
                   Status status,
                   List<String> tags,
                   int senderAddressID,
                   int receiverAddressID,
                   int licencePlate,
                   int customerID,
                   int paymentID) {
        this.packageID = packageID;
        this.weight = weight;
        this.volume = volume;
        this.status = status;
        this.tags = tags;
        this.senderAddressID = senderAddressID;
        this.receiverAddressID = receiverAddressID;
        this.licencePlate = licencePlate;
        this.customerID = customerID;
        this.paymentID = paymentID;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public int getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(int licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
}
