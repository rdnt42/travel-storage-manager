package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.error.UnExpectedException;
import com.summerdev.travelstoragemanager.error.HotelExecuteException;
import com.summerdev.travelstoragemanager.service.UpdaterErrorHandlerService;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 10.01.2022
 * Time: 22:40
 */
@Slf4j
@Service
public class HotelUpdaterErrorHandlerImpl implements UpdaterErrorHandlerService {

    @Override
    public void handleError(Exception e, RunnableTask task) {
        if (e instanceof HotelExecuteException exception) {
            if (exception.getCode() == HotelExecuteException.HotelError.LOCATION_NOT_FOUND_ERROR.getCode()) {
                log.warn("Some expected error in task " + task.getTaskId() + ", message: " + e.getMessage());
            }
        } else {
            throw new UnExpectedException(e);
        }
    }
}
