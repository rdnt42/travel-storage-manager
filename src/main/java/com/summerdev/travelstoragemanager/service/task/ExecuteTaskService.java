package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.11.2021
 * Time: 22:35
 */
public interface ExecuteTaskService {
    void executeTask(RunnableTask runnableTask);
}
