package com.server.DataAccessObject;

import com.server.ModelClass.Storage;

import java.util.List;

public interface StorageDao {

    void insertStorage();

    List<Storage> getStoragesByCourierID(int courierID);
}
