package com.server.ServiceClass;

import com.server.DataAccessObject.AddressDao;
import com.server.DataAccessObject.ComplaintDao;
import com.server.ModelClass.Complaint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    private final ComplaintDao complaintDao;

    public ComplaintService(ComplaintDao complaintDao) {
        this.complaintDao = complaintDao;
    }

    public void insertComplaint(Complaint complaint) {
        complaintDao.insertComplaint(complaint);
    }

    public List<Complaint> getComplaints() {
        return complaintDao.getComplaints();
    }

    public void deleteComplaint(int complaintID) {
        complaintDao.deleteComplaint(complaintID);
    }
}
