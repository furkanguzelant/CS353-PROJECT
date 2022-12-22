package com.server.ControllerClass;

import com.server.ModelClass.Address;
import com.server.ModelClass.Package;
import com.server.ModelClass.Payment;
import com.server.ModelClass.Step;
import com.server.ModelClass.Users.User;
import com.server.ServiceClass.AddressService;
import com.server.ServiceClass.PackageService;
import com.server.ServiceClass.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "api/package")
public class PackageController {

    private final PackageService packageService;
    private final AddressService addressService;
    private final PaymentService paymentService;

    public PackageController(PackageService packageService, AddressService addressService,
                             PaymentService paymentService) {
        this.packageService = packageService;
        this.addressService = addressService;
        this.paymentService = paymentService;
    }

    @PostMapping("/insertPackage")
    void insertPackage(@RequestBody NewPackageDTO newPackageDTO) {
        List<Integer> senderAddressList = addressService.getAddressIdListOfCustomer(newPackageDTO.pack.getSenderID());
        List<Integer> receiverAddressList = addressService.getAddressIdListOfCustomer(newPackageDTO.pack.getReceiverID());

        newPackageDTO.pack.setSenderAddressID(senderAddressList.get(0));
        newPackageDTO.pack.setReceiverAddressID(receiverAddressList.get(0));
        int packageID = packageService.insertPackage(newPackageDTO.pack);

        newPackageDTO.payment.setPackageID(packageID);
        paymentService.addPayment(newPackageDTO.payment);

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

        public NewPackageDTO(Package pack, Payment payment) {
            this.pack = pack;
            this.payment = payment;
        }
    }
}
