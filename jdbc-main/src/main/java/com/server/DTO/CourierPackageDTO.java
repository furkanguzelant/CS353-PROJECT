package com.server.DTO;

import com.server.ModelClass.Address;
import com.server.ModelClass.Package;

public class CourierPackageDTO {

    private Package pack;
    private Address addressToDeliver;

    public CourierPackageDTO() {
    }

    public CourierPackageDTO(Package pack, Address addressToDeliver) {
        this.pack = pack;
        this.addressToDeliver = addressToDeliver;
    }


    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public Address getAddressToDeliver() {
        return addressToDeliver;
    }

    public void setAddressToDeliver(Address addressToDeliver) {
        this.addressToDeliver = addressToDeliver;
    }

}
