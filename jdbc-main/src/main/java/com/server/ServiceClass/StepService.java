package com.server.ServiceClass;

import com.server.DataAccessObject.PaymentDao;
import com.server.DataAccessObject.StepDao;
import com.server.ModelClass.Step;
import org.springframework.stereotype.Service;

@Service
public class StepService {

    private final StepDao stepDao;

    public StepService(StepDao stepDao) {
        this.stepDao = stepDao;
    }

    public void createStep(Step step) {
        stepDao.createStep(step);
    }
}
