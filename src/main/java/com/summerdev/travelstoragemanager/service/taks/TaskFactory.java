package com.summerdev.travelstoragemanager.service.taks;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.entity.TaskType.TaskTypes;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:27
 */
@Service
public class TaskFactory {

    public RunnableTask getTask(InfoTask task, TaskTypes taskType) {
        RunnableTask runnableTask;

        switch (taskType) {
            case TASK_GET_HOTELS_INFO:
                runnableTask = new HotelsInfoTask(task);
                break;

            case TASK_GET_TRAINS_INFO:
                runnableTask = new TrainsInfoTask(task);
                break;

            default:
                throw new IllegalArgumentException("Wrong task type: " + taskType.getIdValue());
        }

        return runnableTask;
    }
}
