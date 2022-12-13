package com.server.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return null;
    }

    // Returns id of inserted user
    public int insertUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = """
                INSERT INTO users(name, birthdate)
                VALUES (?, ?);
                 """;
        String finalSql = sql;
        jdbcTemplate.update(conn -> {

            // Pre-compiling SQL
            PreparedStatement preparedStatement = conn.prepareStatement(finalSql, Statement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setString(1, user.getName());
            preparedStatement.setObject(2, user.getBirthDate());

            return preparedStatement;

        }, keyHolder);

        Integer id = (Integer) keyHolder.getKeys().get("userid");
        return id;
    }

    @Override
    public int insertCustomer(User user) {

        Integer id = insertUser(user);
        System.out.println(id);

        String sql = """
                INSERT INTO customer(userID)
                VALUES (?);
                 """;
        jdbcTemplate.update(
                sql,
                id
        );
        return id;
    }

    @Override
    public int insertRegisteredCustomer(RegisteredCustomer registeredCustomer) {
        Integer id = insertCustomer(registeredCustomer);
        System.out.println(id);

        String sql = """
                INSERT INTO registeredCustomer(userID, email, password, phoneNumber)
                VALUES (?, ?, ?, ?);
                 """;
        return jdbcTemplate.update(
                sql,
                id, registeredCustomer.getEmail(), registeredCustomer.getPassword(),
                registeredCustomer.getPhoneNumber()
        );
    }

    public List<User> selectCustomers() {
        var sql = """
                SELECT *
                FROM users natural join customer
                 """;
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new User(resultSet.getInt("userID"),
                    resultSet.getString("name"),
                    LocalDate.parse(resultSet.getString("birthDate"))
            );
        });
    }


}
