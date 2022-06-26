package com.summerdev.travelstoragemanager.service.task.runnable;

import com.summerdev.travelstoragemanager.error.BusinessLogicException;
import com.summerdev.travelstoragemanager.service.ThreadPoolTaskService;
import com.summerdev.travelstoragemanager.serviceType.HotelLookServiceType;
import com.summerdev.travelstoragemanager.service.task.ExecuteTaskService;
import com.summerdev.travelstoragemanager.service.task.InfoTaskStateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:30
 */
@Slf4j
@AllArgsConstructor
@Service
@Scope(value = "prototype")
public final class HotelsInfoTask extends RunnableTask implements HotelLookServiceType {
    private final InfoTaskStateService infoTaskStateService;
    private final ExecuteTaskService executeTaskService;
    private final ThreadPoolTaskService threadPoolTaskService;

    @Override
    public void run() {
        try {
            executeTaskService.executeTask(this);
            infoTaskStateService.disableAndDeleteTask(taskId);
            log.info("Data about all Hotels has been updated");
        } catch (BusinessLogicException e) {
            changeStateOnError(e);
        } catch (Exception e) {
            e.printStackTrace();
            threadPoolTaskService.startTaskWithDelay(this, 1);
        }
    }

    private void changeStateOnError(BusinessLogicException e) {
        if (e.getCode() == BusinessLogicException.BusinessError.TOO_MANY_REQUESTS_ERROR.getCode()) {
            threadPoolTaskService.startTaskWithDelay(this, 1);
            log.warn("Rate limit exceeded for task id: {}. Task will be postpone", taskId);
        } else if (e.getCode() == BusinessLogicException.BusinessError.EMPTY_ERROR_CODE.getCode()) {
            threadPoolTaskService.startTaskWithDelay(this, 1);
            log.warn("Rate limit exceeded for task id: {}. . Task will be postpone", taskId);
        }
    }
}
