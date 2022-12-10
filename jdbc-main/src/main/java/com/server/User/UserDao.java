package com.server.User;

import java.util.List;

public interface UserDao {

    int insertCustomer(User user);
    int insertRegisteredCustomer(RegisteredCustomer registeredCustomer);
    List<User> selectCustomers();

}
