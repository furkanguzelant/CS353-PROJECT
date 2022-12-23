package com.server.ModelClass;

public class Address {

    private Integer addressID;
    private String country;
    private String city;
    private String district;
    private String zipcode;
    private String addressInfo;

    public Address(Integer addressID,
                   String country,
                   String city,
                   String district,
                   String zipcode,
                   String addressInfo) {
        this.addressID = addressID;
        this.country = country;
        this.city = city;
        this.district = district;
        this.zipcode = zipcode;
        this.addressInfo = addressInfo;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }
}
