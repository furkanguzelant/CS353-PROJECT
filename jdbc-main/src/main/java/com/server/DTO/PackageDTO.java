package com.server.DTO;

import com.server.ModelClass.Address;
import com.server.ModelClass.Package;
import com.server.ModelClass.Payment;

public class PackageDTO {
    private Package pack;
    private Payment payment;
    private Address address;

    public PackageDTO(Package pack, Payment payment, Address address) {
        this.pack = pack;
        this.payment = payment;
        this.address = address;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}