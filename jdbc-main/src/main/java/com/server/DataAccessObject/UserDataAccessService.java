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

        List<User> users;
        var sql = """
                SELECT *
                FROM registeredCustomer natural join users
                WHERE email = ? AND password = ?
                 """;

        users = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(RegisteredCustomer.class),
                email, password);

        if(users.size() == 0) {

            sql = """
                SELECT *
                FROM staff natural join users
                WHERE email = ? AND password = ?
                 """;

            users = jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper(Staff.class),
                    email, password);

        }
        if(users.size() == 0)
            return null;
        return users.get(0);
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
        return jdbcTemplate.update(
                sql,
                id, registeredCustomer.getEmail(), registeredCustomer.getPassword(),
                registeredCustomer.getPhoneNumber()
        );
    }

    public int insertStaff(Staff staff) {
        Integer id = insertUser(staff);
        String sql = """
                INSERT INTO staff(userID, email, password, phoneNumber, salary)
                VALUES (?, ?, ?, ?, ?);
                 """;
        jdbcTemplate.update(
                sql,
                id, staff.getEmail(), staff.getPassword(),
                staff.getPhoneNumber(), staff.getSalary()
        );
        return id;

    }

    @Override
    public int insertEmployee(Employee employee) {
        Integer id = insertStaff(employee);
        String sql = """
                INSERT INTO employee(userID, email)
                VALUES (?, ?);
                 """;
        return jdbcTemplate.update(
                sql,
                id, employee.getLogisticUnitID()
        );
    }

    public int insertCourier(Courier courier) {
        Integer id = insertStaff(courier);
        String sql = """
                INSERT INTO courier(userID, status, logisticUnitID)
                VALUES (?, ?, ?);
                 """;
        return jdbcTemplate.update(
                sql,
                id, courier.getStatus(), courier.getLogisticUnitID()
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
                    LocalDate.parse(resultSet.getString("birthDate")),
                    UserRoleConstraints.UNREGISTERED_CUSTOMER_TYPE);
        });
    }


}
