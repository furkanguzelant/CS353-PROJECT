package com.server.DataAccessObject;

import com.server.Enums.ComplaintType;
import com.server.ModelClass.Complaint;
import com.server.RowMappers.ComplaintRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ComplaintDataAccessService implements ComplaintDao{

    private final JdbcTemplate jdbcTemplate;
    KeyHolder keyHolder = new GeneratedKeyHolder();

    public ComplaintDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertComplaint(Complaint complaint) {

        String sql = """
                INSERT INTO complaint(complaintID, packageID, registeredCustomerID, typeOfComplaint, message)
                VALUES (?,?,?,?,?);
                 """;
        jdbcTemplate.update(
                sql,
                complaint.getComplaintID(),
                complaint.getPackageID(),
                complaint.getRegisteredCustomerID(),
                complaint.getTypeOfComplaint(),
                complaint.getMessage()
        );
    }
    @Override
    public List<Complaint> selectAllComplaints() {
        var sql = """
                SELECT *
                FROM complaint
                 """;

        return jdbcTemplate.query(sql, new ComplaintRowMapper());
    }

    @Override
    public Optional<Complaint> getComplaintById(int complaintID) {
        var sql = """
                SELECT *
                FROM complaint
                WHERE complaintID = ?
                 """;


        return jdbcTemplate.query(sql, new ComplaintRowMapper(), complaintID)
                .stream()
                .findFirst();
    }

    @Override
    public List<Complaint> getComplaintByPackageId(int packageID) {

        var sql = """
            SELECT *
            FROM  complaint
            WHERE packageID = ?
             """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Complaint(
                    resultSet.getInt("complaintID"),
                    resultSet.getInt("packageID"),
                    resultSet.getInt("registeredCustomerID"),
                    ComplaintType.fromInteger(
                            resultSet.getInt("typeOfComplaint")
                    ),
                    resultSet.getString("message")
            );
        });
    }
}

