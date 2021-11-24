package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;

import static com.summerdev.travelstoragemanager.entity.TaskType.TaskTypes;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:08
 */
public interface InfoTaskService {
    InfoTask createTask(TaskTypes taskTypeEnum);
}
