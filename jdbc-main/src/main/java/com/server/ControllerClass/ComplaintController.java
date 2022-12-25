package com.server.ControllerClass;

import com.server.ModelClass.Complaint;
import com.server.ServiceClass.ComplaintService;
import com.server.ServiceClass.LogisticUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "api/complaint")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping("/makeComplaint")
    public ResponseEntity<Map<String, Object>> makeComplaint(@RequestBody Complaint complaint) {
        try {
            complaintService.insertComplaint(complaint);
            return new ResponseEntity<>(Map.of("statusMessage", "Complaint created successfully"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Complaint creation failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getComplaints")
    public ResponseEntity<Map<String, Object>> getComplaints() {
        try {
            List<Complaint> complaints = complaintService.getComplaints();
            return new ResponseEntity<>(Map.of("statusMessage", "Complaints fetched successfully",
                    "complaints", complaints), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Complaints fetch failed"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteComplaints(Integer complaintID) {
        try {
            complaintService.deleteComplaint(complaintID);
            return new ResponseEntity<>(Map.of("statusMessage", "Complaint deleted successfully"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("statusMessage", "Complaints deletion failed"), HttpStatus.BAD_REQUEST);
        }
    }
}
