package com.summerdev.travelstoragemanager.service.task.factory;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import static com.summerdev.travelstoragemanager.config.ThreadPoolTaskSchedulerConfig.threadPoolTaskScheduler;

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

    public RunnableTask(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public ScheduledFuture<?> getFuture() {
        return future;
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

    @Override
    public void run() {
        log.error("Method not implement: {}", taskId);
    }
}
