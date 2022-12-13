package com.server.ModelClass;

import java.time.LocalDate;

public class Courier extends Staff{

    enum Status {AVAILABLE, IN_DISTRIBUTION};
    private Status status;

    public Courier() {}

    public Courier(Status status) {
        this.status = status;
    }

    public Courier(String email, String password, String phoneNumber, int salary, Status status) {
        super(email, password, phoneNumber, salary);
        this.status = status;
    }

    public Courier(Integer userID, String name, LocalDate birthDate, String email, String password, String phoneNumber, int salary, Status status) {
        super(userID, name, birthDate, email, password, phoneNumber, salary);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
