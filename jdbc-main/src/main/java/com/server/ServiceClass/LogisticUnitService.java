package com.server.ServiceClass;

import com.server.DataAccessObject.LogisticUnitDao;
import com.server.DataAccessObject.UserDao;
import com.server.ModelClass.LogisticUnits.LogisticUnit;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticUnitService {

    private final LogisticUnitDao logisticUnitDao;

    public LogisticUnitService(LogisticUnitDao logisticUnitDao) {
        this.logisticUnitDao = logisticUnitDao;
    }

    public List<LogisticUnit> getLogisticUnits() {
        return logisticUnitDao.getLogisticUnits();
    }

    public LogisticUnit getLogisticUnitByEmployeeID(int employeeID) {
        return logisticUnitDao.getLogisticUnitByEmployeeID(employeeID);
    }

}
