package com.server.DataAccessObject;

import com.server.ModelClass.LogisticUnits.LogisticUnit;

import java.util.List;

public interface LogisticUnitDao {

    List<LogisticUnit> getLogisticUnits();
    LogisticUnit getLogisticUnitByEmployeeID(int employeeID);
}
