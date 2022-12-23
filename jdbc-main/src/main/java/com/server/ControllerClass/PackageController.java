package com.server.ControllerClass;

import com.server.Enums.ProcessType;
import com.server.ModelClass.Address;
import com.server.ModelClass.LogisticUnits.LogisticUnit;
import com.server.ModelClass.Package;
import com.server.ModelClass.Payment;
import com.server.ModelClass.Step;
import com.server.ModelClass.Users.User;
import com.server.ServiceClass.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/package")
public class PackageController {

    private final PackageService packageService;
    private final AddressService addressService;
    private final PaymentService paymentService;
    private final StepService stepService;
    private final LogisticUnitService logisticUnitService;

    public PackageController(PackageService packageService, AddressService addressService,
                             PaymentService paymentService, StepService stepService,
                             LogisticUnitService logisticUnitService) {
        this.packageService = packageService;
        this.addressService = addressService;
        this.paymentService = paymentService;
        this.stepService = stepService;
        this.logisticUnitService = logisticUnitService;
    }

    @PostMapping("/insertPackage")
    public ResponseEntity<Map<String, Object>> insertPackage(@RequestBody NewPackageDTO newPackageDTO) {

        try {
            List<Integer> senderAddressList = addressService.getAddressIdListOfCustomer(newPackageDTO.pack.getSenderID());
            List<Integer> receiverAddressList = addressService.getAddressIdListOfCustomer(newPackageDTO.pack.getReceiverID());

            newPackageDTO.pack.setSenderAddressID(senderAddressList.get(0));
            newPackageDTO.pack.setReceiverAddressID(receiverAddressList.get(0));
            int packageID = packageService.insertPackage(newPackageDTO.pack);

            newPackageDTO.payment.setPackageID(packageID);
            paymentService.addPayment(newPackageDTO.payment);

            int employeeID = newPackageDTO.employeeID;
            LogisticUnit logisticUnit = logisticUnitService.getLogisticUnitByEmployeeID(employeeID);

            Step step = new Step(null,
                    Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    ProcessType.Receive, packageID, logisticUnit.getAddressID(), null);

            stepService.createStep(step);
            return new ResponseEntity<>(Map.of("statusMessage", "Package created successfully"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Package could not be created"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    List<Package> selectAllPackages() {
        return packageService.selectAllPackages();
    }

    Optional<Package> getPackageById(int packageID) {
        return packageService.getPackageById(packageID);
    }

    List<Step> getStepsOfPackage(int packageID) {
        return packageService.getStepsOfPackage(packageID);
    }

    List<Package> getPackagesByCustomerId(int userID) {
        return packageService.getPackagesByCustomerId(userID);
    }

    private static class NewPackageDTO {
        private Package pack;
        private Payment payment;
        private int employeeID;

        public NewPackageDTO(Package pack, Payment payment, int employeeID) {
            this.pack = pack;
            this.payment = payment;
            this.employeeID = employeeID;
        }
    }
}
