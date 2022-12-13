package com.server.ModelClass.Users;

import com.server.ModelClass.Users.User;

import java.time.LocalDate;

public class RegisteredCustomer extends User {

    private String email;
    private String password;
    private String phoneNumber;

    public RegisteredCustomer() {}
    public RegisteredCustomer(String email, String password, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public RegisteredCustomer(String name, LocalDate birthDate, String type, String email, String password, String phoneNumber) {
        super(name, birthDate, type);
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
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
}
