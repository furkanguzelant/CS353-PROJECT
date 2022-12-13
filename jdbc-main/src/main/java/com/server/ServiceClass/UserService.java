package com.server.ServiceClass;

import com.server.DataAccessObject.UserDao;
import com.server.ModelClass.RegisteredCustomer;
import com.server.ModelClass.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addCustomer(User user) {
        int result = userDao.insertCustomer(user);
    }

    public void addRegisteredCustomer(RegisteredCustomer registeredCustomer) {
        int result = userDao.insertRegisteredCustomer(registeredCustomer);
    }

    public List<User> getCustomers() {
        return userDao.selectCustomers();
    }
}
