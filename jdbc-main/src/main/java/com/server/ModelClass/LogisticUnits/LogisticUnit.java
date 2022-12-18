package com.server.ModelClass.LogisticUnits;

public class LogisticUnit {
    private Integer logisticUnitID;
    private String  name;
    private Integer addressID;

    //Constructors
    public LogisticUnit(Integer logisticUnitID, String name, Integer addressID) {
        this.logisticUnitID = logisticUnitID;
        this.name = name;
        this.addressID = addressID;
    }

    public LogisticUnit(String name) {
        this.name = name;
    }

    public LogisticUnit() {
    }

    //Getter and Setters


    public Integer getLogisticUnitID() {
        return logisticUnitID;
    }

    public void setLogisticUnitID(Integer logisticUnitID) {
        this.logisticUnitID = logisticUnitID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
