package com.server.ModelClass;

import java.util.Date;
import com.server.Enums.ProcessType

public class Step {

    private int stepID;
    private Date receive_date;
    private ProcessType processType;
    private int packageID;
    private int prevAddress;
    private int nextAddress;

    public Step(int stepID,
                Date receive_date,
                ProcessType processType,
                int packageID,
                int prevAddress,
                int nextAddress) {
        this.stepID = stepID;
        this.receive_date = receive_date;
        this.processType = processType;
        this.packageID = packageID;
        this.prevAddress = prevAddress;
        this.nextAddress = nextAddress;
    }

    public int getStepID() {
        return stepID;
    }

    public void setStepID(int stepID) {
        this.stepID = stepID;
    }

    public Date getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(Date receive_date) {
        this.receive_date = receive_date;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public int getPrevAddress() {
        return prevAddress;
    }

    public void setPrevAddress(int prevAddress) {
        this.prevAddress = prevAddress;
    }

    public int getNextAddress() {
        return nextAddress;
    }

    public void setNextAddress(int nextAddress) {
        this.nextAddress = nextAddress;
    }
}
