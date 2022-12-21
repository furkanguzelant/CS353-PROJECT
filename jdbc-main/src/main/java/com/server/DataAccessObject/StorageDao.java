package com.server.DataAccessObject;

import com.server.ModelClass.Storage;

import java.util.List;

public interface StorageDao {

    void insertStorage();

    List<Storage> getStoragesByCourierID(int courierID);

    List<Storage> getStoragesEmployeeID(int employeeID);

    void putPackageIntoStorage(int packageID, int storageID);
}
