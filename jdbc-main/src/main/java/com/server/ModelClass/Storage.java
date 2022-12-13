package com.server.ModelClass;

public class Storage {
    private Integer storageID;
    private Integer maxVolume;
    private Integer currentVolume;
    private Integer logisticUnitID;

    //Constructors
    public Storage(Integer storageID, Integer maxVolume, Integer currentVolume, Integer logisticUnitID) {
        this.storageID = storageID;
        this.maxVolume = maxVolume;
        this.currentVolume = currentVolume;
        this.logisticUnitID = logisticUnitID;
    }

    public Storage(Integer maxVolume, Integer currentVolume, Integer logisticUnitID) {
        this.maxVolume = maxVolume;
        this.currentVolume = currentVolume;
        this.logisticUnitID = logisticUnitID;
    }

    //Getter and setters


    public Integer getStorageID() {
        return storageID;
    }

    public void setStorageID(Integer storageID) {
        this.storageID = storageID;
    }

    public Integer getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(Integer maxVolume) {
        this.maxVolume = maxVolume;
    }

    public Integer getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(Integer currentVolume) {
        this.currentVolume = currentVolume;
    }

    public Integer getLogisticUnitID() {
        return logisticUnitID;
    }

    public void setLogisticUnitID(Integer logisticUnitID) {
        this.logisticUnitID = logisticUnitID;
    }
}
