package com.server.ModelClass.Users;
import java.time.LocalDate;

public class User {

    private Integer userID;
    private String name;
    private LocalDate birthDate;
    private String type;

    public User(Integer userID, String name, LocalDate birthDate, String type) {
        this.userID = userID;
        this.name = name;
        this.birthDate = birthDate;
        this.type = type;
    }

    public User(String name, LocalDate birthDate, String type) {
        this.name = name;
        this.birthDate = birthDate;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
