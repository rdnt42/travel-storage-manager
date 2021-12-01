package com.summerdev.travelstoragemanager.service.task.factory;

import com.summerdev.travelstoragemanager.error.BusinessLogicException;
import com.summerdev.travelstoragemanager.service.task.HotelExecuteTaskServiceImpl;
import com.summerdev.travelstoragemanager.service.task.InfoTaskStateService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:30
 */
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelsInfoTask extends RunnableTask {
    @NonNull InfoTaskStateService infoTaskStateService;
    @NonNull HotelExecuteTaskServiceImpl hotelExecuteTaskService;

    public HotelsInfoTask(Long taskId,
                          @NonNull InfoTaskStateService infoTaskStateService,
                          @NonNull HotelExecuteTaskServiceImpl hotelExecuteTaskService) {
        super(taskId);
        this.infoTaskStateService = infoTaskStateService;
        this.hotelExecuteTaskService = hotelExecuteTaskService;
    }

    @Override
    public void run() {
        try {
            hotelExecuteTaskService.executeTask(this);
            infoTaskStateService.disableAndDeleteTask(taskId);
        } catch (BusinessLogicException e) {
            changeStateOnError(e);
        } catch (Exception e) {
            e.printStackTrace();
            startTaskWithDelay(1);
        }
    }
}
