package com.server.ServiceClass;

import com.server.DataAccessObject.PackageDao;
import com.server.DataAccessObject.PaymentDao;
import com.server.ModelClass.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentDao paymentDao;

    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public void addPayment(Payment payment) {
        paymentDao.insertPayment(payment);
    }

    public Payment getPaymentByPackageID(int packageID) {
        return paymentDao.getPaymentByPackageID(packageID);
    }

    public void updatePaymentStatus(int packageID, int paymentStatus) {
        paymentDao.updatePaymentStatus(packageID, paymentStatus);
    }
}
