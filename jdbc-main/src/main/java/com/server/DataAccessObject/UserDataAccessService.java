package com.server.DataAccessObject;

import com.server.ModelClass.Users.*;
import com.server.Utility.UserRoleConstraints;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {

        User user;
        var sql = """
                SELECT *
                FROM registeredCustomer natural join users
                WHERE email = ? AND password = ?
                 """;

        user = (RegisteredCustomer) jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper(RegisteredCustomer.class),
                new Object[]{email, password});

        if(user == null) {

            sql = """
                SELECT *
                FROM staff natural join users
                WHERE email = ? AND password = ?
                 """;

            user = (Staff) jdbcTemplate.queryForObject(sql,
                    new BeanPropertyRowMapper(Staff.class),
                    new Object[]{email, password});
        }
        return user;
    }



    // Returns id of inserted user
    public int insertUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = """
                INSERT INTO users(name, birthdate, type)
                VALUES (?, ?, ?);
                 """;
        String finalSql = sql;
        jdbcTemplate.update(conn -> {

            // Pre-compiling SQL
            PreparedStatement preparedStatement = conn.prepareStatement(finalSql, Statement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setString(1, user.getName());
            preparedStatement.setObject(2, user.getBirthDate());
            preparedStatement.setString(3, user.getType());

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
        jdbcTemplate.update(
                sql,
                id, registeredCustomer.getEmail(), registeredCustomer.getPassword(),
                registeredCustomer.getPhoneNumber()
        );
        return id;
    }

    public List<User> selectCustomers() {
        var sql = """
                SELECT *
                FROM users natural join customer
                 """;
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new User(resultSet.getInt("userID"),
                    resultSet.getString("name"),
                    LocalDate.parse(resultSet.getString("birthDate")),
                    UserRoleConstraints.UNREGISTERED_CUSTOMER_TYPE);
        });
    }


}
