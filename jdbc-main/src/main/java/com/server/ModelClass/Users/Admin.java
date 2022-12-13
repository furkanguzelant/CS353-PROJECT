package com.server.ModelClass.Users;

import com.server.ModelClass.Users.Staff;

import java.time.LocalDate;

public class Admin extends Staff {

    public Admin() {
    }

    public Admin(String email, String password, String phoneNumber, int salary) {
        super(email, password, phoneNumber, salary);
    }

    public Admin(Integer userID, String name, LocalDate birthDate, String type,
                 String email, String password, String phoneNumber, int salary) {
        super(userID, name, birthDate, type, email, password, phoneNumber, salary);
    }


}
