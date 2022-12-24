package com.server.ModelClass;

import java.util.Date;
import com.server.Enums.ProcessType;

public class Step {

    private Integer stepID;
    private Date receive_date;
    private ProcessType processType;
    private Integer packageID;
    private Integer prevAddress;
    private Integer nextAddress;

    public Step(Integer stepID,
                Date receive_date,
                ProcessType processType,
                Integer packageID,
                Integer prevAddress,
                Integer nextAddress) {
        this.stepID = stepID;
        this.receive_date = receive_date;
        this.processType = processType;
        this.packageID = packageID;
        this.prevAddress = prevAddress;
        this.nextAddress = nextAddress;
    }

    public Integer getStepID() {
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

    public Integer getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }

    public Integer getPrevAddress() {
        return prevAddress;
    }

    public void setPrevAddress(int prevAddress) {
        this.prevAddress = prevAddress;
    }

    public Integer getNextAddress() {
        return nextAddress;
    }

    public void setNextAddress(int nextAddress) {
        this.nextAddress = nextAddress;
    }
}
