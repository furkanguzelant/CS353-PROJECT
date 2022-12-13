package com.server.DataAccessObject;

import com.server.ModelClass.Users.RegisteredCustomer;
import com.server.ModelClass.Users.User;

import java.util.List;

public interface UserDao {

    int insertCustomer(User user);
    int insertRegisteredCustomer(RegisteredCustomer registeredCustomer);
    List<User> selectCustomers();
    User getUserByEmailAndPassword( String email, String password);

}
