package com.server.ModelClass.LogisticUnits;

import com.server.ModelClass.LogisticUnits.LogisticUnit;

public class DistributionCenter extends LogisticUnit {

    //Constructor

    public DistributionCenter(Integer logisticUnitID, String name, Integer addressID) {
        super(logisticUnitID, name, addressID);
    }

    public DistributionCenter(String name) {
        super(name);
    }

    public DistributionCenter() {
    }

    //Getters and Setters

}
