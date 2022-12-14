package com.server.ServiceClass;

import com.server.DataAccessObject.AddressDao;
import com.server.ModelClass.Address;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AddressService {

    private final AddressDao addressDao;

    public AddressService(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public int insertAddress(Address address) {
        return addressDao.insertAddress(address);
    }

    public void bindAddressToCustomer( int addressId, int customerId){
        addressDao.bindAddressToCustomer( addressId,customerId);
    }


}
