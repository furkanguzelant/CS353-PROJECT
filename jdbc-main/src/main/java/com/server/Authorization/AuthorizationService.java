package com.server.Authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.server.Utility.UserRoleConstraints;

public class AuthorizationService {
    private static AuthorizationService authorizer;
    private static String key = "cancancancanyılmaz";
    private static Algorithm algorithm;

    public static AuthorizationService getInstance() {
        if (authorizer == null) {
            authorizer = new AuthorizationService();
            algorithm = Algorithm.HMAC256(key);
        }
        return authorizer;
    }

    public int getUserIdOfToken(String token) throws JWTDecodeException {

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("fancy_ghost_logistics")
                .acceptExpiresAt(600)
                .withClaimPresence("roleID")
                .withClaimPresence("userID")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims().get("userID").asInt();
    }

    public boolean verifyTokenAsAdmin(String token) throws JWTDecodeException {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("fancy_ghost_logistics")
                .acceptExpiresAt(600)
                .withClaimPresence("roleID")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims().get("roleID").asString().equals(UserRoleConstraints.ADMIN_TYPE);
    }

    public boolean verifyTokenAsCourier(String token) throws JWTDecodeException {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("fancy_ghost_logistics")
                .acceptExpiresAt(600)
                .withClaimPresence("roleID")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims().get("roleID").asString().equals(UserRoleConstraints.COURIER_TYPE);
    }

    public boolean verifyTokenAsEmployee(String token) throws JWTDecodeException {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("fancy_ghost_logistics")
                .acceptExpiresAt(600)
                .withClaimPresence("roleID")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims().get("roleID").asString().equals(UserRoleConstraints.EMPLOYEE_TYPE);
    }

    public boolean verifyTokenAsRegisteredCustomer(String token) throws JWTDecodeException {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("fancy_ghost_logistics")
                .acceptExpiresAt(600)
                .withClaimPresence("roleID")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims().get("roleID").asString().equals(UserRoleConstraints.REGISTERED_CUSTOMER_TYPE);
    }


}
