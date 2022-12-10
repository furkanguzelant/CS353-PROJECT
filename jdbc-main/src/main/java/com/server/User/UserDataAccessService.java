package com.server.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertCustomer(User user) {
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
        System.out.println(id);

        sql = """
                INSERT INTO customer(userID)
                VALUES (?);
                 """;
        return jdbcTemplate.update(
                sql,
                id
        );


    }
}
