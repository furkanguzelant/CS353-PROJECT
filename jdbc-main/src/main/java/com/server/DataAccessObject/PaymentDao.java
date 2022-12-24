package com.server.DataAccessObject;

import com.server.ModelClass.Payment;

import java.util.List;

public interface PaymentDao {
    void insertPayment(Payment payment);
    Payment getPaymentByPackageID(int packageID);
}
