package com.server.Authorization;

import com.server.ModelClass.Users.User;
import com.server.DataAccessObject.UserDao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthorizationService {
    private final UserDao userDao ;
    private final String key = "asfdasfdasdfasfasdfsdafadfasdfasdfasdfasfasdf";

    public AuthorizationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Map<String, Object> authenticateAccount(String email, String password) {
        Map<String, Object> returnMap = new HashMap<>();

        User user = userDao.getUserByEmailAndPassword( email, password);

        return null;
    }
}
