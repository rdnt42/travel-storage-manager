package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.service.UpdaterErrorHandlerService;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.codec.DecodingException;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 10.01.2022
 * Time: 23:17
 */
@Slf4j
@Service
public class TrainUpdaterErrorHandlerImpl implements UpdaterErrorHandlerService {
    @Override
    public void handleError(Exception e, RunnableTask task) throws Exception {
        if (e instanceof DecodingException) {
            log.warn("Some expected error in task " + task.getTaskId() + ", message: " + e.getMessage());
        } else {
            throw e;
        }
    }
}
