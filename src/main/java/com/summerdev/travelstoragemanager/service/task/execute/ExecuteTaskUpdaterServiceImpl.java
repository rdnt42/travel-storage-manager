package com.summerdev.travelstoragemanager.service.task.execute;

import com.summerdev.travelstoragemanager.aspect.LogUpdateCount;
import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.TravelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.UpdaterErrorHandlerService;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.storage.ServiceTypeServiceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.06.2022
 * Time: 23:01
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExecuteTaskUpdaterServiceImpl implements ExecuteTaskUpdaterService {
    private final ServiceTypeServiceStorage<TravelInfoUpdaterService> travelInfoUpdaterServiceStorage;
    private final ServiceTypeServiceStorage<UpdaterErrorHandlerService> updaterErrorServiceStorage;
    private final ServiceTypeServiceStorage<CursorService> cursorServiceStorage;

    private final InfoTaskRepository infoTaskRepository;

    @Override
    @LogUpdateCount
    public int updateTravelInfo(RunnableTask runnableTask, InfoTask task) {
        try {
            TravelInfoUpdaterService updater = travelInfoUpdaterServiceStorage.getService(runnableTask.getServiceTypeClass());

            return updater.updateTravelInfo(task.getCursorId());
        } catch (Exception e) {
            log.error("Error in task: {}, cursor: {}, text: {}", task.getId(), task.getCursorId(), e.getMessage());
            UpdaterErrorHandlerService handler = updaterErrorServiceStorage.getService(runnableTask.getServiceTypeClass());
            handler.handleError(e, runnableTask);

            return 0;
        }
    }

    @Override
    public void updateNextCursor(RunnableTask runnableTask, InfoTask task) {
        CursorService cursorService = cursorServiceStorage.getService(runnableTask.getServiceTypeClass());
        Long nextCursor = cursorService.getNextCursorId(task.getCursorId());
        task.setCursorId(nextCursor);

        infoTaskRepository.save(task);
    }
}
