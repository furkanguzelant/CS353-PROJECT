package com.server.ModelClass.Users;
import java.time.LocalDate;

public class User {

    private Integer userID;
    private String name;
    private LocalDate birthDate;

    public User() {
    }

    public User(Integer userID, String name, LocalDate birthDate) {
        this.userID = userID;
        this.name = name;
        this.birthDate = birthDate;
    }

    public User(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
