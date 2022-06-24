package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.aspect.LogUpdateCount;
import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.TravelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.UpdaterErrorHandlerService;
import com.summerdev.travelstoragemanager.service.factory.CursorFactory;
import com.summerdev.travelstoragemanager.service.factory.TravelInfoUpdaterFactory;
import com.summerdev.travelstoragemanager.service.factory.UpdaterErrorHandlerFactory;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class ExecuteTaskUpdaterServiceImpl implements ExecuteTaskUpdaterService {

    TravelInfoUpdaterFactory travelInfoUpdaterFactory;
    UpdaterErrorHandlerFactory updaterErrorHandlerFactory;
    CursorFactory cursorFactory;
    InfoTaskRepository infoTaskRepository;

    @Override
    @LogUpdateCount
    public int updateTravelInfo(RunnableTask runnableTask, Long cursor, Long id) {
        try {
            TravelInfoUpdaterService updater = travelInfoUpdaterFactory.getTravelInfoUpdaterService(runnableTask);

            return updater.updateTravelInfo(cursor);
        } catch (Exception e) {
            log.error("Error in task: {}, cursor: {}, text: {}", id, cursor, e.getMessage());
            UpdaterErrorHandlerService handler = updaterErrorHandlerFactory.getUpdaterErrorHandlerService(runnableTask);
            handler.handleError(e, runnableTask);

            return 0;
        }
    }

    @Override
    public void updateNextCursor(RunnableTask runnableTask, InfoTask task, Long cursor) {
        CursorService cursorService = cursorFactory.getCursorService(runnableTask);
        Long nextCursor = cursorService.getNextCursorId(cursor);
        task.setCursorId(nextCursor);

        infoTaskRepository.save(task);
    }
}
