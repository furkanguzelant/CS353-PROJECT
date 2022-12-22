package com.server.ServiceClass;

import com.server.DataAccessObject.UserDao;
import com.server.ModelClass.Users.Courier;
import com.server.ModelClass.Users.Employee;
import com.server.ModelClass.Users.RegisteredCustomer;
import com.server.ModelClass.Users.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int addCustomer(User user) {
        return userDao.insertCustomer(user);
    }

    public int addRegisteredCustomer(RegisteredCustomer registeredCustomer) {
        return userDao.insertRegisteredCustomer(registeredCustomer);
    }

    public List<User> getCustomers() {
        return userDao.selectCustomers();
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password);
    }

    public int createEmployee(Employee employee) {
        return userDao.insertEmployee(employee);
    }

    public int createCourier(Courier courier) {
        return userDao.insertCourier(courier);
    }
}
