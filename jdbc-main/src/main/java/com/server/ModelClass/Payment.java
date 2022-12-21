package com.server.ModelClass;

enum Type { PREPAID,COUNTER_PAID}
enum Status { NOT_PAID,PAID}

public class Payment {
    int price;
    Type type;
    Status status;
    int packageID;

    public Payment(int price, Type type, Status status, int packageID) {
        this.price = price;
        this.type = type;
        this.status = status;
        this.packageID = packageID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }
}
