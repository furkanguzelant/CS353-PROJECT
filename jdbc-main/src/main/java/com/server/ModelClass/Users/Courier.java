package com.server.ModelClass.Users;

import com.server.Enums.CourierStatus;
import com.server.ModelClass.Users.Staff;

import java.time.LocalDate;

public class Courier extends Staff {

    private CourierStatus status;
    private int logisticUnitID;

    public Courier() {}

    public Courier(CourierStatus status, int logisticUnitID) {
        this.status = status;
        this.logisticUnitID = logisticUnitID;
    }

    public Courier(String email, String password, String phoneNumber, int salary, CourierStatus status, int logisticUnitID) {
        super(email, password, phoneNumber, salary);
        this.status = status;
        this.logisticUnitID = logisticUnitID;
    }

    public Courier(String name, LocalDate birthDate,String type, String email, String password, String phoneNumber, int salary, CourierStatus status) {
        super(name, birthDate, type, email, password, phoneNumber, salary);
        this.status = status;
    }

    public CourierStatus getStatus() {
        return status;
    }

    public void setStatus(CourierStatus status) {
        this.status = status;
    }

    public int getLogisticUnitID() {
        return logisticUnitID;
    }

    public void setLogisticUnitID(int logisticUnitID) {
        this.logisticUnitID = logisticUnitID;
    }
}
