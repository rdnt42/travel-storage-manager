package com.summerdev.travelstoragemanager.service.factory;

import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.hotelInfo.HotelCursorServiceImpl;
import com.summerdev.travelstoragemanager.service.task.runnable.HotelsInfoTask;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.service.task.runnable.TrainsInfoTask;
import com.summerdev.travelstoragemanager.service.trainInfo.TrainCursorServiceImpl;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 10.01.2022
 * Time: 22:58
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class CursorFactory {
    @NonNull HotelCursorServiceImpl hotelCursorService;
    @NonNull TrainCursorServiceImpl trainCursorService;

    public CursorService getCursorService(RunnableTask runnableTask) {
        if (runnableTask instanceof HotelsInfoTask) {
            return hotelCursorService;
        } else if (runnableTask instanceof TrainsInfoTask) {
            return trainCursorService;
        } else {
            throw new IllegalArgumentException("Unknown runnable class: " + runnableTask.getClass());
        }
    }
}
