package com.server.ControllerClass;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.server.Authorization.AuthorizationService;
import com.server.ModelClass.Users.*;
import com.server.ServiceClass.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="createUser/customer")
    public void addCustomer(@RequestBody User user) {
        userService.addCustomer(user);
    }

    @PostMapping(path="createUser/regCustomer")
    public void addRegisteredCustomer(@RequestBody RegisteredCustomer registeredCustomer) {
        userService.addRegisteredCustomer(registeredCustomer);
    }

    @GetMapping(path="getUser/customer")
    public List<User> getCustomers() {
        return userService.getCustomers();
    }

    @PostMapping(path="createUser/staff")
    public ResponseEntity<Map<String, Object>> addStaff (@RequestHeader("access-token") String accessToken, @RequestBody Staff staff) {
        try {
            if (AuthorizationService.getInstance().verifyTokenAsAdmin( accessToken )){
                if(staff.getType().equals("E")) {
                    userService.createEmployee((Employee) staff);
                }
                else {
                    userService.createCourier((Courier) staff);
                }
                return new ResponseEntity<>(Map.of("statusMessage", "Staff is successfully created"), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(Map.of("statusMessage", "Unauthorized"), HttpStatus.UNAUTHORIZED);
            }
        } catch (JWTDecodeException exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Invalid token"), HttpStatus.BAD_REQUEST);
        }
    }
}
