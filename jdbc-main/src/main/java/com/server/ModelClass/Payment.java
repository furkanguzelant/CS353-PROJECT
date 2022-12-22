package com.server.ModelClass;

import com.server.Enums.PaymentStatus;
import com.server.Enums.PaymentType;


public class Payment {
    int price;
    PaymentType type;
    PaymentStatus status;
    int packageID;

    public Payment(int price, PaymentType type, PaymentStatus status, int packageID) {
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

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }
}
