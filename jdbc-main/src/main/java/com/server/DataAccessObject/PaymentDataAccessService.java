package com.server.DataAccessObject;

import com.server.Enums.PaymentStatus;
import com.server.Enums.PaymentType;
import com.server.ModelClass.LogisticUnits.LogisticUnit;
import com.server.ModelClass.Payment;
import com.server.ServiceClass.PaymentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public Payment getPaymentByPackageID(int packageID) {
        String sql = """
                SELECT *
                FROM payment natural join package
                WHERE packageID = ?
                 """;

        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            return new Payment(
                    resultSet.getInt("price"),
                    PaymentType.fromInteger(resultSet.getInt("type")),
                    PaymentStatus.fromInteger(resultSet.getInt("status")),
                    resultSet.getInt("packageid")
            );
        }, packageID);

    }

}
