package com.server.DataAccessObject;

import com.server.ModelClass.Complaint;

import java.util.List;
import java.util.Optional;

public interface ComplaintDao {

    void insertComplaint(Complaint complaint);
    List<Complaint> getComplaints();
    void deleteComplaint(int complaintID);
}
