package com.server.DataAccessObject;

import com.server.Enums.ProcessType;
import com.server.ModelClass.LogisticUnits.LogisticUnit;
import com.server.ModelClass.Step;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogisticUnitDataAccessService implements LogisticUnitDao {

    private final JdbcTemplate jdbcTemplate;
    KeyHolder keyHolder = new GeneratedKeyHolder();

    public LogisticUnitDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<LogisticUnit> getLogisticUnits() {
        var sql = """
                SELECT *
                FROM  logisticUnit
                 """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new LogisticUnit(
                    resultSet.getInt("logisticUnitID"),
                    resultSet.getString("name"),
                    resultSet.getInt("addressID")
            );
        });
    }

    public LogisticUnit getLogisticUnitByEmployeeID(int employeeID) {
        var sql = """
                SELECT logisticUnitID, name, addressID
                FROM  logisticUnit natural join employee
                WHERE employee.userID = ?""";

        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            return new LogisticUnit(
                    resultSet.getInt("logisticUnitID"),
                    resultSet.getString("name"),
                    resultSet.getInt("addressID")
            );
        }, employeeID);
    }

}
