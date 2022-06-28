package com.summerdev.travelstoragemanager.service.task.execute;

import com.summerdev.travelstoragemanager.error.BusinessLogicException;
import com.summerdev.travelstoragemanager.service.ThreadPoolTaskService;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.06.2022
 * Time: 23:23
 */
@Slf4j
@AllArgsConstructor
@Service
public class ExecuteTaskErrorHandlerServiceImpl implements ExecuteTaskErrorHandlerService {
    private final ThreadPoolTaskService threadPoolTaskService;


    @Override
    public void changeStateOnError(RunnableTask runnableTask, long errorCode) {
        if (errorCode == BusinessLogicException.BusinessError.TOO_MANY_REQUESTS_ERROR.getCode()) {
            threadPoolTaskService.startTaskWithDelay(runnableTask, 1);
            log.warn("Rate limit exceeded for task id: {}. Task will be postpone", runnableTask.getTaskId());
        } else if (errorCode == BusinessLogicException.BusinessError.EMPTY_ERROR_CODE.getCode()) {
            threadPoolTaskService.startTaskWithDelay(runnableTask, 3);
            log.warn("Unexpected error for task id: {}. Task will be postpone", runnableTask.getTaskId());
        }
    }

    @Override
    public void changeStateOnError(RunnableTask runnableTask) {
        threadPoolTaskService.startTaskWithDelay(runnableTask, 1);
    }
}
