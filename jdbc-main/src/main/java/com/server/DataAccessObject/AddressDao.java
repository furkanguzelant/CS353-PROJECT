package com.server.DataAccessObject;

import com.server.ModelClass.Address;
import com.server.ModelClass.Users.User;

import java.sql.SQLException;

public interface AddressDao {
    int insertAddress(Address address) ;
    void bindAddressToCustomer( int addressID, int customerID );
}
