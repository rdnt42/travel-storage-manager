package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;

import static com.summerdev.travelstoragemanager.entity.TaskType.TaskTypeEnum;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:08
 */
public interface InfoTaskService {
    InfoTask createTask(TaskTypeEnum taskTypeEnum);

    void initTasks();
}