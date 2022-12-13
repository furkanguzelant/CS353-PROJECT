package com.server.ModelClass;

import java.time.LocalDate;

public class Staff extends User{

    private String email;
    private String password;
    private String phoneNumber;
    private int salary;

    public Staff() {}
    public Staff(String email, String password, String phoneNumber, int salary) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public Staff(Integer userID, String name, LocalDate birthDate, String email, String password, String phoneNumber, int salary) {
        super(userID, name, birthDate);
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
