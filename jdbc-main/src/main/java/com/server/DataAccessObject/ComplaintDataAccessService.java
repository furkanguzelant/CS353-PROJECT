package com.server.DataAccessObject;

import com.server.Enums.ComplaintType;
import com.server.ModelClass.Complaint;
import com.server.ModelClass.LogisticUnits.LogisticUnit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                INSERT INTO complaint(userID, packageID, type, message)
                VALUES (?,?,?,?);
                 """;

        jdbcTemplate.update(sql, complaint.getRegisteredCustomerID(),
                complaint.getPackageID(),
                complaint.getTypeOfComplaint().ordinal(),
                complaint.getMessage());

    }

    public List<Complaint> getComplaints() {
        var sql = """
                SELECT *
                FROM complaint
                 """;

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Complaint(
                    resultSet.getInt("complaintID"),
                    resultSet.getInt("userID"),
                    resultSet.getInt("packageID"),
                    ComplaintType.fromInteger(resultSet.getInt("type")),
                    resultSet.getString("message")
            );
        }
        );
    }

    public void deleteComplaint(int complaintID) {
        var sql = """
                DELETE FROM complaint WHERE complaintID = ?
                 """;

        jdbcTemplate.update(sql, complaintID);
    }
}
