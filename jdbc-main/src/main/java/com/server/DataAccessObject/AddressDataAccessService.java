package com.server.DataAccessObject;

import com.server.ModelClass.Address;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


@Repository
public class AddressDataAccessService implements AddressDao {

    private final JdbcTemplate jdbcTemplate;
    KeyHolder keyHolder = new GeneratedKeyHolder();

    public AddressDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertAddress(Address address)  {

        String sql = """
                INSERT INTO address(country, city, district,zipcode,addressinfo)
                VALUES (?, ?, ?, ?, ?);
                """;
        String finalSql = sql;

        jdbcTemplate.update(conn -> {

            // Pre-compiling SQL
            PreparedStatement preparedStatement = conn.prepareStatement(finalSql, Statement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getDistrict());
            preparedStatement.setString(4, address.getZipcode());
            preparedStatement.setString(5, address.getAddressInfo());

            return preparedStatement;

        }, keyHolder);

        Integer id = (Integer) keyHolder.getKeys().get("addressid");
        return id;
    }

    @Override
    public void bindAddressToCustomer(int addressID, int customerID) {

        String sql = """
                INSERT INTO customer_address(customerid, addressid)
                VALUES (?, ?);
                """;

        jdbcTemplate.update(sql,customerID,addressID);
    }
}
