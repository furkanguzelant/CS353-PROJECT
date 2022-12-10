package com.server.User;

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
