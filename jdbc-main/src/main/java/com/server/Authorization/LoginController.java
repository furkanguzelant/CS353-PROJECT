package com.server.Authorization;

import com.server.ModelClass.Address;
import com.server.ModelClass.Users.RegisteredCustomer;
import com.server.ModelClass.Users.User;
import com.server.ServiceClass.AddressService;
import com.server.ServiceClass.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class LoginController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final AddressService addressService;

    public LoginController(AuthenticationService authenticationService, UserService userService, AddressService addressService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Map<String, Object>> login(String email, String password) {
        Map<String, Object> returnMap = authenticationService.authenticateAccount(email, password);
        if(returnMap.get("token") == null) {
            return new ResponseEntity<>(returnMap, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

    @PostMapping(path = "/registerCustomer")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequestBody registerRequestBody){
        try {
            int customerId = userService.addRegisteredCustomer( registerRequestBody.registeredCustomer );
            int addressId = addressService.insertAddress(registerRequestBody.address);
            addressService.bindAddressToCustomer(addressId,customerId);
        }
        catch ( Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Registeration Failed!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Map.of("statusMessage", "Successfully Registered."), HttpStatus.OK);
    }

    private static class RegisterRequestBody{
        private RegisteredCustomer registeredCustomer;
        private Address address;

        public RegisterRequestBody(RegisteredCustomer registeredCustomer, Address address) {
            this.registeredCustomer = registeredCustomer;
            this.address = address;
        }
    }

}
