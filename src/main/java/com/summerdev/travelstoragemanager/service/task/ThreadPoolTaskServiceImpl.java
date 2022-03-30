package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.service.ThreadPoolTaskService;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 30.03.2022
 * Time: 21:05
 */
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class ThreadPoolTaskServiceImpl implements ThreadPoolTaskService {
    @NonNull ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public void startTask(RunnableTask task) {
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task, new Date());
        task.setFuture(future);
        log.info("Task started immediately, id: {}", task.getTaskId());
    }

    @Override
    public void startTaskWithDelay(RunnableTask task, int interval) {
        Date date = getNextStartTime(interval);
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task, date);
        task.setFuture(future);
        log.info("Task will start at {}, id: {}", date, task.getTaskId());
    }

    public void startTaskWithShortInitDelay(RunnableTask task) {
        Date date = getDateWithShortDelay();
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task, date);
        task.setFuture(future);
        log.info("Task will start at {}, id: {}", date, task.getTaskId());
    }

    private Date getDateWithShortDelay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 1);

        return calendar.getTime();
    }

    private Date getNextStartTime(int intervalInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, intervalInMinutes);

        return calendar.getTime();
    }

    @Override
    public void stopTask(RunnableTask task) {
        if (task.getFuture() != null) {
            task.getFuture().cancel(true);
            log.info("Task stopped, id: {}", task.getTaskId());
        }
    }


}
