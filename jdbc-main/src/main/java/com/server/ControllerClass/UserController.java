package com.server.ControllerClass;

import com.server.ModelClass.Users.Courier;
import com.server.ModelClass.Users.Employee;
import com.server.ModelClass.Users.RegisteredCustomer;
import com.server.ModelClass.Users.User;
import com.server.ServiceClass.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(path="createUser/employee")
    public void addEmployee(@RequestBody Employee employee) {
        userService.createEmployee(employee);
    }

    @PostMapping(path="createUser/courier")
    public void addCourier(@RequestBody Courier courier) {
        userService.createCourier(courier);
    }

}
