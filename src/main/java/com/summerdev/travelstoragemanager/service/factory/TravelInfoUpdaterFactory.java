package com.summerdev.travelstoragemanager.service.factory;

import com.summerdev.travelstoragemanager.service.TravelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.hotelInfo.HotelInfoUpdaterServiceImpl;
import com.summerdev.travelstoragemanager.service.task.runnable.HotelsInfoTask;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.service.task.runnable.TrainsInfoTask;
import com.summerdev.travelstoragemanager.service.trainInfo.TrainInfoUpdaterServiceImpl;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 10.01.2022
 * Time: 21:59
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TravelInfoUpdaterFactory {

    @NonNull TrainInfoUpdaterServiceImpl trainInfoUpdaterService;
    @NonNull HotelInfoUpdaterServiceImpl hotelInfoUpdaterService;

    public TravelInfoUpdaterService getTravelInfoUpdaterService(RunnableTask runnableTask) {
        TravelInfoUpdaterService service;

        if (HotelsInfoTask.class.equals(runnableTask.getClass())) {
            service = hotelInfoUpdaterService;
        } else if (TrainsInfoTask.class.equals(runnableTask.getClass())) {
            service = trainInfoUpdaterService;
        } else {
            throw new IllegalArgumentException("Unknown runnable class: " + runnableTask.getClass());
        }

        return service;
    }
}
