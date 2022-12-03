package com.summerdev.travelstoragemanager.service.factory;

import com.summerdev.travelstoragemanager.enums.TaskTypes;
import com.summerdev.travelstoragemanager.service.task.runnable.HotelsInfoTask;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.service.task.runnable.TrainsInfoTask;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:27
 */
@Service
public abstract class RunnableTaskFactory {
    public RunnableTask getTask(TaskTypes taskType) {
        return switch (taskType) {
            case TASK_GET_HOTELS_INFO -> getHotelsInfoTask();
            case TASK_GET_TRAINS_INFO -> getTrainsInfoTask();
            default -> throw new IllegalArgumentException("Wrong task type: " + taskType.getIdValue());
        };
    }

    @Lookup
    protected abstract HotelsInfoTask getHotelsInfoTask();

    @Lookup
    protected abstract TrainsInfoTask getTrainsInfoTask();
}
