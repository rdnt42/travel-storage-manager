package com.summerdev.travelstoragemanager.service.task.execute;

import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.06.2022
 * Time: 23:22
 */
public interface ExecuteTaskErrorHandlerService {
    /**
     *
     * @param errorCode expectedError
     * @param runnableTask running task
     */
    void changeStateOnError(RunnableTask runnableTask, long errorCode);

    /**
     * unexpected error
     *
     * @param runnableTask running task
     */
    void changeStateOnError(RunnableTask runnableTask);
}
