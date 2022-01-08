package com.summerdev.travelstoragemanager.service.task.factory;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.entity.TaskType.TaskTypes;
import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.task.HotelExecuteTaskServiceImpl;
import com.summerdev.travelstoragemanager.service.travelInfo.TrainsInfoServiceImpl;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:27
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TaskFactory {

    @NonNull TrainsInfoServiceImpl trainsInfoServiceImpl;
    @NonNull HotelExecuteTaskServiceImpl hotelExecuteTaskService;

    @NonNull HotelsInfoTask hotelsInfoTask;
    @NonNull TrainsInfoTask trainsInfoTask;

    public RunnableTask getTask(InfoTask task, TaskTypes taskType) {
        RunnableTask runnableTask;

        switch (taskType) {
            case TASK_GET_HOTELS_INFO:
                runnableTask = hotelsInfoTask;
                break;

            case TASK_GET_TRAINS_INFO:
                runnableTask = trainsInfoTask;
                break;

            default:
                throw new IllegalArgumentException("Wrong task type: " + taskType.getIdValue());
        }

        runnableTask.setTaskId(task.getId());

        return runnableTask;
    }

    // FIXME разнести в разные сервисы
    public CursorService getCursorService(TaskTypes taskType) {
        CursorService service;
        switch (taskType) {
            case TASK_GET_HOTELS_INFO:
                service = hotelExecuteTaskService;
                break;

            case TASK_GET_TRAINS_INFO:
                service = trainsInfoServiceImpl;
                break;

            default:
                throw new IllegalArgumentException("Wrong task type: " + taskType.getIdValue());
        }

        return service;
    }
}
