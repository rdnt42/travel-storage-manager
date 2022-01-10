package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.service.UpdaterErrorHandlerService;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 10.01.2022
 * Time: 23:17
 */
@Service
public class TrainUpdaterErrorHandlerImpl implements UpdaterErrorHandlerService {
    @Override
    public void handleError(Exception e, RunnableTask task) throws Exception {
        throw e;
    }
}
