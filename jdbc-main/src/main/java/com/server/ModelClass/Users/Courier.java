package com.server.ModelClass.Users;

import com.server.Enums.CourierStatus;
import com.server.ModelClass.Users.Staff;

import java.time.LocalDate;

public class Courier extends Staff {

    private CourierStatus status;

    public Courier() {}

    public Courier(CourierStatus status) {
        this.status = status;
    }

    public Courier(String email, String password, String phoneNumber, int salary, CourierStatus status) {
        super(email, password, phoneNumber, salary);
        this.status = status;
    }

    public Courier(Integer userID, String name, LocalDate birthDate,String type, String email, String password, String phoneNumber, int salary, CourierStatus status) {
        super(userID, name, birthDate, type, email, password, phoneNumber, salary);
        this.status = status;
    }

    public CourierStatus getStatus() {
        return status;
    }

    public void setStatus(CourierStatus status) {
        this.status = status;
    }
}
