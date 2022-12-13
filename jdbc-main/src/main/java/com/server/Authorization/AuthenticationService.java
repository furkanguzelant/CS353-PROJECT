package com.server.Authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.server.ModelClass.*;
import com.server.DataAccessObject.UserDao;
import com.server.Utility.UserRoleConstraints;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthenticationService {
    private final UserDao userDao;
    private final String key = "cancancancanyılmaz";

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Map<String, Object> authenticateAccount(String email, String password) {
        Map<String, Object> returnMap = new HashMap<>();

        User user = userDao.getUserByEmailAndPassword(email, password);
        if (user != null) {
            Algorithm algorithm = Algorithm.HMAC256(key);
            returnMap.put("userID", user.getUserID());

            if (user instanceof Admin) {
                String token = JWT.create()
                        .withIssuer("fancy_ghost_logistics")
                        .withClaim("roleID", UserRoleConstraints.ADMIN_TYPE)
                        .withClaim("userID", user.getUserID())
                        .sign(algorithm);
                returnMap.put("token", token);
                returnMap.put("type", "admin");
            } else if (user instanceof Courier) {
                String token = JWT.create()
                        .withIssuer("fancy_ghost_logistics")
                        .withClaim("roleID", UserRoleConstraints.COURIER_TYPE)
                        .withClaim("userID", user.getUserID())
                        .sign(algorithm);
                returnMap.put("token", token);
                returnMap.put("type", "courier");
            } else if (user instanceof Employee) {
                String token = JWT.create()
                        .withIssuer("fancy_ghost_logistics")
                        .withClaim("roleID", UserRoleConstraints.EMPLOYEE_TYPE)
                        .withClaim("userID", user.getUserID())
                        .sign(algorithm);
                returnMap.put("token", token);
                returnMap.put("type", "employee");
            } else if (user instanceof RegisteredCustomer) {
                String token = JWT.create()
                        .withIssuer("fancy_ghost_logistics")
                        .withClaim("roleID", UserRoleConstraints.REGISTERED_CUSTOMER_TYPE)
                        .withClaim("userID", user.getUserID())
                        .sign(algorithm);
                returnMap.put("token", token);
                returnMap.put("type", "registeredCustomer");
            }
            return returnMap;
        }

        returnMap.put("token", null);
        return returnMap;
    }
}
