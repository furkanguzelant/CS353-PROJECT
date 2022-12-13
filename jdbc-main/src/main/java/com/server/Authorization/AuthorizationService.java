package com.server.Authorization;

import com.server.User.UserDao;
import com.server.User.UserDataAccessService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class AuthorizationService {
    private final UserDao userDao ;
    private final String key = "asfdasfdasdfasfasdfsdafadfasdfasdfasdfasfasdf";

    public AuthorizationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Map<String, Object> authenticateAccount(String email, String password) {
        return null;
    }
}
