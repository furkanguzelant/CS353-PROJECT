package com.server.DataAccessObject;

import com.server.ModelClass.Address;
import com.server.ModelClass.Users.User;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    int insertAddress(Address address) ;
    void bindAddressToCustomer( int addressID, int customerID );
    List<Integer> getAddressIdListOfCustomer(int customerID);
    Address getAddressByAddressID(int addressID);
}
