package com.server.ServiceClass;

import com.server.DataAccessObject.StorageDao;
import com.server.ModelClass.Storage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {
    private final StorageDao storageDao;

    public StorageService(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    public List<Storage> getStoragesByCourierID(int courierID) {
        return storageDao.getStoragesByCourierID(courierID);
    }

    public List<Storage> getStoragesByEmployeeID(int employeeID) {
        return storageDao.getStoragesEmployeeID(employeeID);
    }

    public void insertPackageToStorage(int packageID, int storageID) {
        storageDao.insertPackageToStorage( packageID, storageID);
    }
}
