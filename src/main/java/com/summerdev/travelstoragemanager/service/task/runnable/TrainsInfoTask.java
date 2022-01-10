package com.summerdev.travelstoragemanager.service.task.runnable;

import com.summerdev.travelstoragemanager.error.BusinessLogicException;
import com.summerdev.travelstoragemanager.service.task.ExecuteTaskService;
import com.summerdev.travelstoragemanager.service.task.InfoTaskStateService;
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
    @NonNull ExecuteTaskService executeTaskService;

    public TrainsInfoTask(@NonNull InfoTaskStateService infoTaskStateService,
                          @NonNull ExecuteTaskService executeTaskService) {
        this.infoTaskStateService = infoTaskStateService;
        this.executeTaskService = executeTaskService;
    }

    @Override
    public void run() {
        try {
            executeTaskService.executeTask(this);
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
