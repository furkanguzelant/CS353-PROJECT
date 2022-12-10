package com.server.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addCustomer(User user) {
        int result = userDao.insertCustomer(user);
    }
}
