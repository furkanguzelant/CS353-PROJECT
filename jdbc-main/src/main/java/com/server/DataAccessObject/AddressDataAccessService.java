package com.server.DataAccessObject;

import com.server.ModelClass.Address;
import com.server.ModelClass.Storage;
import com.server.ModelClass.Users.RegisteredCustomer;
import com.server.ModelClass.Users.User;
import com.server.Utility.UserRoleConstraints;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;


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

    @Override
    public List<Integer> getAddressIdListOfCustomer(int customerID) {
        var sql = """
                SELECT addressID
                FROM customer_address
                WHERE customerID = ?

                 """;

        return jdbcTemplate.queryForList(sql,Integer.class, customerID);

    }

    public Address getAddressByAddressID(int addressID) {

        var sql = """
                SELECT *
                FROM address
                WHERE addressID = ?

                 """;

        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            return new Address(
                    resultSet.getInt("addressID"),
                    resultSet.getString("country"),
                    resultSet.getString("city"),
                    resultSet.getString("district"),
                    resultSet.getString("zipcode"),
                    resultSet.getString("addressInfo")
            );
        }, addressID);
    }
}
