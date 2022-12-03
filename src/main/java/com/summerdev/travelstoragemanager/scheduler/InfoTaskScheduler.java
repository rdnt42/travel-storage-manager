package com.summerdev.travelstoragemanager.scheduler;

import com.summerdev.travelstoragemanager.service.task.InfoTaskService;
import com.summerdev.travelstoragemanager.service.task.InfoTaskStateService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.summerdev.travelstoragemanager.enums.TaskTypes.TASK_GET_HOTELS_INFO;
import static com.summerdev.travelstoragemanager.enums.TaskTypes.TASK_GET_TRAINS_INFO;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:22
 */

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class InfoTaskScheduler {
    @NonNull InfoTaskService infoTaskService;
    @NonNull InfoTaskStateService infoTaskStateService;

    @Scheduled(cron = "0 0 0 * * *")
    public void createTasksInfo() {
        infoTaskStateService.enableTask(infoTaskService.createTask(TASK_GET_HOTELS_INFO));
        infoTaskStateService.enableTask(infoTaskService.createTask(TASK_GET_TRAINS_INFO));
    }
}
