package com.summerdev.travelstoragemanager.service.task.factory;

import com.summerdev.travelstoragemanager.error.BusinessLogicException;
import com.summerdev.travelstoragemanager.service.task.InfoTaskStateService;
import com.summerdev.travelstoragemanager.service.task.TrainExecuteTaskServiceImpl;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:32
 */
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
@Scope("prototype")
public class TrainsInfoTask extends RunnableTask {
    @NonNull InfoTaskStateService infoTaskStateService;
    @NonNull TrainExecuteTaskServiceImpl trainExecuteTaskService;

    public TrainsInfoTask(@NonNull InfoTaskStateService infoTaskStateService, @NonNull TrainExecuteTaskServiceImpl trainExecuteTaskService) {
        this.infoTaskStateService = infoTaskStateService;
        this.trainExecuteTaskService = trainExecuteTaskService;
    }

    @Override
    public void run() {
        try {
            trainExecuteTaskService.executeTask(this);
            infoTaskStateService.disableAndDeleteTask(taskId);
            log.info("Data about all Trains has been updated");
        } catch (BusinessLogicException e) {
            changeStateOnError(e);
        } catch (Exception e) {
            e.printStackTrace();
            startTaskWithDelay(1);
        }
    }
}
