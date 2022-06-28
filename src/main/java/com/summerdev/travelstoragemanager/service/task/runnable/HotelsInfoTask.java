package com.summerdev.travelstoragemanager.service.task.runnable;

import com.summerdev.travelstoragemanager.error.BusinessLogicException;
import com.summerdev.travelstoragemanager.service.task.InfoTaskStateService;
import com.summerdev.travelstoragemanager.service.task.execute.ExecuteTaskErrorHandlerService;
import com.summerdev.travelstoragemanager.service.task.execute.ExecuteTaskService;
import com.summerdev.travelstoragemanager.serviceType.HotelLookServiceType;
import com.summerdev.travelstoragemanager.serviceType.ServiceType;
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
public final class HotelsInfoTask extends RunnableTask {
    private final InfoTaskStateService infoTaskStateService;
    private final ExecuteTaskService executeTaskService;
    private final ExecuteTaskErrorHandlerService executeTaskErrorHandlerService;

    @Override
    public void run() {
        try {
            executeTaskService.executeTask(this);
            infoTaskStateService.disableAndDeleteTask(taskId);
            log.info("Data about all Hotels has been updated");
        } catch (BusinessLogicException e) {
            executeTaskErrorHandlerService.changeStateOnError(this, e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            executeTaskErrorHandlerService.changeStateOnError(this);
        }
    }

    @Override
    public Class<? extends ServiceType> getServiceTypeClass() {
        return HotelLookServiceType.class;
    }
}
