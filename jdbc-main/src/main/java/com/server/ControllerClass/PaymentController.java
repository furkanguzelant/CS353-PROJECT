package com.server.ControllerClass;

import com.server.Enums.PaymentStatus;
import com.server.ServiceClass.PaymentService;
import com.server.ServiceClass.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/makePayment")
    public ResponseEntity<Map<String, Object>> makePayment(int packageID) {
        try {
            paymentService.updatePaymentStatus(packageID, PaymentStatus.PAID.ordinal());
            return new ResponseEntity<>(Map.of("statusMessage", "Payment successful"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Payment failed"), HttpStatus.BAD_REQUEST);
        }
    }

}
