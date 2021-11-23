package com.summerdev.travelstoragemanager.service.taks;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.entity.TaskType;

import static com.summerdev.travelstoragemanager.entity.TaskType.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:08
 */
public interface InfoTaskService {
    void enableTask(InfoTask task);

    void disableTask(InfoTask task);

    void createTask(TaskTypes taskTypeEnum);
}
