package com.server.ControllerClass;

import com.server.ModelClass.LogisticUnits.LogisticUnit;
import com.server.ServiceClass.LogisticUnitService;
import com.server.ServiceClass.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "api/logisticUnit")
public class LogisticUnitController {

    private final LogisticUnitService logisticUnitService;

    public LogisticUnitController(LogisticUnitService logisticUnitService) {
        this.logisticUnitService = logisticUnitService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getLogisticUnits() {
        try {
            List<LogisticUnit> logisticUnitList = logisticUnitService.getLogisticUnits();
            return new ResponseEntity<>(Map.of("statusMessage", "Successfully fetched.", "logisticUnitList", logisticUnitList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("statusMessage", "Fetch operation failed"), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
