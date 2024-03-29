package com.server.ControllerClass;

import com.server.DTO.CourierPackageDTO;
import com.server.DTO.PackageDTO;
import com.server.DTO.PackageStatisticsInfo;
import com.server.Enums.PackageStatus;
import com.server.Enums.ProcessType;
import com.server.ModelClass.*;
import com.server.ModelClass.LogisticUnits.LogisticUnit;
import com.server.ModelClass.Package;
import com.server.ServiceClass.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/package")
public class PackageController {

    private final PackageService packageService;
    private final StorageService storageService;
    private final AddressService addressService;
    private final PaymentService paymentService;
    private final StepService stepService;
    private final LogisticUnitService logisticUnitService;
    private final VehicleService vehicleService;

    public PackageController(PackageService packageService, AddressService addressService,
                             PaymentService paymentService, StepService stepService,
                             LogisticUnitService logisticUnitService, StorageService storageService,
                             VehicleService vehicleService) {
        this.packageService = packageService;
        this.addressService = addressService;
        this.paymentService = paymentService;
        this.stepService = stepService;
        this.logisticUnitService = logisticUnitService;
        this.storageService = storageService;
        this.vehicleService = vehicleService;
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
                    ProcessType.Receive, packageID, senderAddressList.get(0), logisticUnit.getAddressID());

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


    @GetMapping("/displayPackagesByEmployeeID")
    public List<PackageDTO> displayPackagesByEmployeeID(@RequestParam int employeeID) {

        List<PackageDTO> employeePackageList = new ArrayList<>();
        List<PackageDTO> packages = packageService.getPackagesInStorageByEmployeeID(employeeID);

        return packages;
    }


    @PutMapping
    public ResponseEntity<Map<String, Object>> assignPackageToCourier(@RequestBody List<Integer> packageIDList,
                                                                      @RequestParam Integer courierID,
                                                                      @RequestParam @Nullable Integer logisticUnitID,
                                                                      @RequestParam Integer employeeID) {

        try {
            LogisticUnit logisticUnit = logisticUnitService.getLogisticUnitByEmployeeID(employeeID);
            for (Integer packageID : packageIDList) {
                Integer nextAddressID;
                ProcessType status;
                if (logisticUnitID != null && logisticUnitID != 0) {
                    nextAddressID = logisticUnitService.getAddressIDOfLogisticUnit(logisticUnitID);
                    status = ProcessType.Transfer;
                } else {
                    nextAddressID = packageService.getPackageById(packageID).getReceiverAddressID();
                    status = ProcessType.Deliver;
                }
                packageService.assignPackageToCourier(packageID, courierID);
                Step step = new Step(null,
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        status, packageID, logisticUnit.getAddressID(), nextAddressID);
                stepService.createStep(step);
            }
            return new ResponseEntity<>(Map.of("statusMessage", "Packages are assigned to courier " + courierID + " successfully"), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Packages could not be assigned"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updatePackageStatus")
    public ResponseEntity<Map<String, Object>> updatePackageStatus(int packageID, int packageStatus) {
        try {
            packageService.updatePackageStatus(packageID, packageStatus);
            return new ResponseEntity<>(Map.of("statusMessage", "Package status updated"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Package status could not be updated"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getIncomingPackagesOfCustomer")
    public ResponseEntity<Map<String, Object>> getIncomingPackagesOfCustomer(@RequestParam int customerID) {
        try {
            List<PackageDTO> customerPackages = packageService.getIncomingPackagesOfCustomer(customerID);
            return new ResponseEntity<>(Map.of("statusMessage", "Successfully fetched", "List", customerPackages), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Fetch failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getSentPackagesOfCustomer")
    public ResponseEntity<Map<String, Object>> getSentPackagesOfCustomer(@RequestParam int customerID) {
        try {
            List<PackageDTO> customerPackages = packageService.getSentPackagesOfCustomer(customerID);
            return new ResponseEntity<>(Map.of("statusMessage", "Successfully fetched", "List", customerPackages), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Fetch failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getPackagesFilterByWeight")
    public ResponseEntity<Map<String, Object>> getPackagesFilterByWeight(@RequestParam int upperWeightLimit, @RequestParam int lowerWeightLimit) {
        try {
            List<PackageDTO> packages = packageService.getPackagesFilterByWeight(upperWeightLimit,lowerWeightLimit);
            return new ResponseEntity<>(Map.of("statusMessage", "Successfully fetched", "List", packages), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Fetch failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getPackagesFilterByCity")
    public ResponseEntity<Map<String, Object>> getPackagesFilterByCity(@RequestParam String inputString) {
        try {
            List<PackageDTO> packages = packageService.getPackagesFilterByCity(inputString);
            return new ResponseEntity<>(Map.of("statusMessage", "Successfully fetched", "List", packages), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Fetch failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getPackageStatistics")
    public ResponseEntity<Map<String, Object>> getPackageStatistics() {
        try {
            PackageStatisticsInfo packageStatisticsInfo = packageService.getPackageStatistics();
            return new ResponseEntity<>(Map.of("statusMessage", "Statistics fetched successfully",
                    "packageStatisticsInfo", packageStatisticsInfo), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Fetching package statistics failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getPackagesOfCourier")
    public ResponseEntity<Map<String, Object>> getPackagesOfCourier(int courierID) {
        try {
            List<CourierPackageDTO> packageInfo = packageService.getPackagesOfCourier(courierID);
            return new ResponseEntity<>(Map.of("statusMessage", "Packages fetched successfully",
                    "packageInfo", packageInfo), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Fetching package failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getPackagesInVehicleOfCourier")
    public  ResponseEntity<Map<String, Object>> getPackagesInVehicleOfCourier(int courierID) {
        try {
            List<CourierPackageDTO> packageInfo = packageService.getPackagesInVehicleOfCourier(courierID);
            return new ResponseEntity<>(Map.of("statusMessage", "Packages fetched successfully",
                    "packageInfo", packageInfo), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Fetching package failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/confirmDelivery")
    public ResponseEntity<Map<String, Object>> confirmDelivery(int packageID) {
        try {
            packageService.updatePackageStatus(packageID, PackageStatus.Delivered.ordinal());
            vehicleService.removePackageFromVehicle(packageID);
            return new ResponseEntity<>(Map.of("statusMessage", "Package status updated successfully"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Package status update failed"), HttpStatus.BAD_REQUEST);
        }
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
