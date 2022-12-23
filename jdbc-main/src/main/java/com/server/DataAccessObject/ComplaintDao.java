package com.server.DataAccessObject;

import com.server.ModelClass.Complaint;

import java.util.List;
import java.util.Optional;

public interface ComplaintDao {

    void insertComplaint(Complaint complaint);

    List<Complaint> selectAllComplaints();

    Optional<Complaint> getComplaintById(int complaintID);

    List<Complaint> getComplaintByPackageId(int packageID);

}
