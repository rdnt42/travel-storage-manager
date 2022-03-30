package com.summerdev.travelstoragemanager.service.task.runnable;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 22:25
 */
@Slf4j
@Getter
@Setter
@Service
public class RunnableTask implements Runnable {
    protected Long taskId;
    protected ScheduledFuture<?> future;

    @Override
    public void run() {
        log.error("Method not implement: {}", taskId);
    }
}
