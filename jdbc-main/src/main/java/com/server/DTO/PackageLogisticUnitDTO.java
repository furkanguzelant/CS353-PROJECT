package com.server.DTO;

import com.server.ModelClass.Address;
import com.server.ModelClass.LogisticUnits.LogisticUnit;
import com.server.ModelClass.Package;

public class PackageLogisticUnitDTO {

    private Package pack;
    private Address packageAddress;
    private LogisticUnit address;

    public PackageLogisticUnitDTO(Package pack, Address packageAddress, LogisticUnit address) {
        this.pack = pack;
        this.packageAddress = packageAddress;
        this.address = address;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public Address getPackageAddress() {
        return packageAddress;
    }

    public void setPackageAddress(Address packageAddress) {
        this.packageAddress = packageAddress;
    }

    public LogisticUnit getAddress() {
        return address;
    }

    public void setAddress(LogisticUnit address) {
        this.address = address;
    }
}
