package com.summerdev.travelstoragemanager.service;

import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 10.01.2022
 * Time: 22:45
 */
public interface UpdaterErrorHandlerService {
    void handleError(Exception e, RunnableTask task);
}
