package com.server.DataAccessObject;

import com.server.ModelClass.Users.Courier;
import com.server.ModelClass.Users.Employee;
import com.server.ModelClass.Users.RegisteredCustomer;
import com.server.ModelClass.Users.User;

import java.util.List;

public interface UserDao {

    int insertCustomer(User user);
    int insertRegisteredCustomer(RegisteredCustomer registeredCustomer);
    int insertEmployee(Employee employee);
    int insertCourier(Courier courier);
    List<User> selectCustomers();
    User getUserByEmailAndPassword( String email, String password);


}
