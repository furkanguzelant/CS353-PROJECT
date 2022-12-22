package com.server.DataAccessObject;

import com.server.ModelClass.Payment;
import com.server.ServiceClass.PaymentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDataAccessService implements PaymentDao {

    private final JdbcTemplate jdbcTemplate;

    public PaymentDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertPayment(Payment payment) {

        String sql = """
                INSERT INTO payment(price, type, status, packageid)
                VALUES (?, ?, ?, ?);
                 """;
        jdbcTemplate.update(
                sql,
                payment.getPrice(), payment.getType().ordinal(), payment.getStatus().ordinal(), payment.getPackageID()
        );

    }
}
