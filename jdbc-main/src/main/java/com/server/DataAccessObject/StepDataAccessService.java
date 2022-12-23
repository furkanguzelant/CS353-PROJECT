package com.server.DataAccessObject;

import com.server.ModelClass.Step;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StepDataAccessService implements StepDao {

    private final JdbcTemplate jdbcTemplate;

    public StepDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createStep(Step step) {

        String sql = """
                INSERT INTO step(receivedate, packageid, prevaddressid, nextaddressid, processtype)
                VALUES (?, ?, ?, ?, ?);
                 """;
        jdbcTemplate.update(
                sql,
                step.getReceive_date(),
                step.getPackageID(),
                step.getPrevAddress(),
                step.getNextAddress(),
                step.getProcessType().ordinal()
        );


    }
}
