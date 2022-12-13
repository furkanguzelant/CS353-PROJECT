package com.server.ModelClass;

public class Complaint {

    enum Type {BrokenPackage, LatePackage, LostPackage}

    private int ComplaintID;
    private int packageID;
    private int registeredCustomerID;
    private Type typeOfComplaint;
    private String message;

    public Complaint(int complaintID,
                     int packageID,
                     int registeredCustomerID,
                     Type typeOfComplaint,
                     String message) {
        ComplaintID = complaintID;
        this.packageID = packageID;
        this.registeredCustomerID = registeredCustomerID;
        this.typeOfComplaint = typeOfComplaint;
        this.message = message;
    }

    public int getComplaintID() {
        return ComplaintID;
    }

    public void setComplaintID(int complaintID) {
        ComplaintID = complaintID;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public int getRegisteredCustomerID() {
        return registeredCustomerID;
    }

    public void setRegisteredCustomerID(int registeredCustomerID) {
        this.registeredCustomerID = registeredCustomerID;
    }

    public Type getTypeOfComplaint() {
        return typeOfComplaint;
    }

    public void setTypeOfComplaint(Type typeOfComplaint) {
        this.typeOfComplaint = typeOfComplaint;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
