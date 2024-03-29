package com.server.ModelClass.Users;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.server.ModelClass.Users.User;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class, name = "E"),
        @JsonSubTypes.Type(value = Courier.class, name = "C")
})
public class Staff extends User {

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

    public Staff(String name, LocalDate birthDate, String type, String email, String password, String phoneNumber, int salary) {
        super(name, birthDate, type);
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
