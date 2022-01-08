package com.summerdev.travelstoragemanager.service.task.factory;

import com.summerdev.travelstoragemanager.error.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static com.summerdev.travelstoragemanager.config.ThreadPoolTaskSchedulerConfig.threadPoolTaskScheduler;
import static com.summerdev.travelstoragemanager.error.BusinessLogicException.BusinessError;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 22:25
 */
@Slf4j
public class RunnableTask implements Runnable {

    protected Long taskId;
    protected ScheduledFuture<?> future;

    public Long getTaskId() {
        return taskId;
    }

    public ScheduledFuture<?> getFuture() {
        return future;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void cancelTask() {
        if (future != null) {
            future.cancel(true);
        }
    }

    public void stopTask() {
        if (future != null) {
            future.cancel(true);
            log.info("Task stopped, id: {}", taskId);
        }
    }

    public void startTask() {
        future = threadPoolTaskScheduler.schedule(this, new Date());
        log.info("Task started immediately, id: {}", taskId);
    }

    public void startTaskWithDelay(long interval) {
        Date date = new Date(new Date().getTime() + (interval * 60000));
        future = threadPoolTaskScheduler.schedule(this, date);
        log.info("Task will start at {}, id: {}", date, taskId);
    }

    protected void changeStateOnError(BusinessLogicException e) {
        if (e.getCode() == BusinessError.TOO_MANY_REQUESTS_ERROR.getCode()) {
            startTaskWithDelay(1);
            log.warn("Rate limit exceeded for task id: {}. Task will start in {} minutes", taskId, future.getDelay(TimeUnit.MINUTES));
        }  else if (e.getCode() == BusinessError.EMPTY_ERROR_CODE.getCode()) {
            startTaskWithDelay(1L);
            log.warn("Rate limit exceeded for task id: {}. Task will start in {} minutes", taskId, future.getDelay(TimeUnit.MINUTES));
        }
    }

    @Override
    public void run() {
        log.error("Method not implement: {}", taskId);
    }
}
