package com.server.ModelClass.Users;

import com.server.ModelClass.Users.Staff;

import java.time.LocalDate;

public class Admin extends Staff {

    public Admin() {
    }

    public Admin(String email, String password, String phoneNumber, int salary) {
        super(email, password, phoneNumber, salary);
    }

    public Admin(String name, LocalDate birthDate, String type,
                 String email, String password, String phoneNumber, int salary) {
        super(name, birthDate, type, email, password, phoneNumber, salary);
    }


}
