package com.server.Authorization;

public class AuthorizationService {
    private static AuthorizationService authorizer;
    private String key = "cancancancanyılmaz";

    public static AuthorizationService getInstance() {
        if(authorizer == null) {
            authorizer = new AuthorizationService();
        }
        return authorizer;
    }





}
