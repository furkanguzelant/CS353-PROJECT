package com.server.ModelClass;

import java.time.LocalDate;

public class Employee extends Staff {

    private int logisticUnitID;

    public Employee(int logisticUnitID) {
        this.logisticUnitID = logisticUnitID;
    }

    public Employee(String email, String password, String phoneNumber, int salary, int logisticUnitID) {
        super(email, password, phoneNumber, salary);
        this.logisticUnitID = logisticUnitID;
    }

    public Employee(Integer userID, String name, LocalDate birthDate, String email, String password, String phoneNumber, int salary, int logisticUnitID) {
        super(userID, name, birthDate, email, password, phoneNumber, salary);
        this.logisticUnitID = logisticUnitID;
    }

    public int getLogisticUnitID() {
        return logisticUnitID;
    }

    public void setLogisticUnitID(int logisticUnitID) {
        this.logisticUnitID = logisticUnitID;
    }
}
