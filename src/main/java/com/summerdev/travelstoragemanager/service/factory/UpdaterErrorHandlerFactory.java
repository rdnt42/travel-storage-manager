package com.summerdev.travelstoragemanager.service.factory;

import com.summerdev.travelstoragemanager.service.UpdaterErrorHandlerService;
import com.summerdev.travelstoragemanager.service.hotelInfo.HotelUpdaterErrorHandlerImpl;
import com.summerdev.travelstoragemanager.service.task.runnable.HotelsInfoTask;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.service.task.runnable.TrainsInfoTask;
import com.summerdev.travelstoragemanager.service.trainInfo.TrainUpdaterErrorHandlerImpl;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 10.01.2022
 * Time: 22:48
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class UpdaterErrorHandlerFactory {
    @NonNull HotelUpdaterErrorHandlerImpl hotelUpdaterErrorHandler;
    @NonNull TrainUpdaterErrorHandlerImpl trainUpdaterErrorHandler;

    public UpdaterErrorHandlerService getUpdaterErrorHandlerService(RunnableTask runnableTask) {
        UpdaterErrorHandlerService service;

        if (HotelsInfoTask.class.equals(runnableTask.getClass())) {
            service = hotelUpdaterErrorHandler;
        } else if (TrainsInfoTask.class.equals(runnableTask.getClass())) {
            service = trainUpdaterErrorHandler;
        } else {
            throw new IllegalArgumentException("Unknown runnable class: " + runnableTask.getClass());
        }

        return service;
    }
}
