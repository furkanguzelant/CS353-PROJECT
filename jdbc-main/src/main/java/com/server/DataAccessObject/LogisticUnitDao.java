package com.server.DataAccessObject;

import com.server.ModelClass.LogisticUnits.LogisticUnit;
import com.server.ModelClass.Users.User;

import java.util.List;

public interface LogisticUnitDao {

    List<LogisticUnit> getLogisticUnits();
    LogisticUnit getLogisticUnitByEmployeeID(int employeeID);
    List<User> getCouriersByEmployeeID(int employeeID);
    Integer getAddressIDOfLogisticUnit(int logisticUnitID);
}
