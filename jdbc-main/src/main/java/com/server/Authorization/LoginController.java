package com.server.Authorization;

import com.server.ServiceClass.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping(path = "api/login")
public class LoginController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public LoginController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(String email, String password) {
        Map<String, Object> returnMap = authenticationService.authenticateAccount(email, password);
        if(returnMap.get("token") == null) {
            return new ResponseEntity<>(returnMap, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

}
