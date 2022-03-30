package com.summerdev.travelstoragemanager.service;

import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 30.03.2022
 * Time: 21:05
 */
public interface ThreadPoolTaskService {
    /**
     * Start task immediately
     *
     * @param task for starting
     */
    void startTask(RunnableTask task);

    /**
     * Start task with delay
     *
     * @param task     for starting
     * @param interval in minutes
     */
    void startTaskWithDelay(RunnableTask task, int interval);

    /**
     * Stop task immediately
     *
     * @param task for stopping
     */
    void stopTask(RunnableTask task);

    /**
     * Init delay need because first time start faster
     * than future assign
     *
     * @param task for starting
     */
    void startTaskWithShortInitDelay(RunnableTask task);
}
