package com.server.ModelClass.LogisticUnits;

public class Branch extends LogisticUnit {


    private Integer distributionCenterID;

    //Constructors
    public Branch(Integer logisticUnitID, String name, Integer distributionCenterID) {
        super(logisticUnitID, name);
        this.distributionCenterID = distributionCenterID;
    }

    //Getters and setters


    public Integer getDistributionCenterID() {
        return distributionCenterID;
    }

    public void setDistributionCenterID(Integer distributionCenterID) {
        this.distributionCenterID = distributionCenterID;
    }
}
