package com.server.ServiceClass;

import com.server.DataAccessObject.PackageDao;
import com.server.DataAccessObject.PaymentDao;
import com.server.ModelClass.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentDao paymentDao;

    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public void addPayment(Payment payment) {
        paymentDao.insertPayment(payment);
    }
}
