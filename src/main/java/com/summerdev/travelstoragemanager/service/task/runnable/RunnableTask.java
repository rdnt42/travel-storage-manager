package com.summerdev.travelstoragemanager.service.task.runnable;

import com.summerdev.travelstoragemanager.service.task.ServiceTypeTask;
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
public abstract class RunnableTask implements Runnable, ServiceTypeTask {
    protected Long taskId;
    protected ScheduledFuture<?> future;
}
