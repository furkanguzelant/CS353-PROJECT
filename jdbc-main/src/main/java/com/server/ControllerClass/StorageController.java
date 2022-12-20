package com.server.ControllerClass;

import com.server.ModelClass.Storage;
import com.server.ServiceClass.StorageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }
    @GetMapping(path="getStoragesByCourierID")
    public List<Storage> getStoragesByCourierID(int courierID) {
        return storageService.getStoragesByCourierID(courierID);
    }
}
