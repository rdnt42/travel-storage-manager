package com.summerdev.travelstoragemanager.service.task;

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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 09.01.2022
 * Time: 21:02
 */
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class ExecuteTaskServiceImpl implements ExecuteTaskService {
    @NonNull InfoTaskRepository infoTaskRepository;
    @NonNull TravelInfoUpdaterFactory travelInfoUpdaterFactory;
    @NonNull UpdaterErrorHandlerFactory updaterErrorHandlerFactory;
    @NonNull CursorFactory cursorFactory;

    @Override
    public void executeTask(RunnableTask runnableTask) throws Exception {

        while (!runnableTask.getFuture().isCancelled()) {
            InfoTask task = infoTaskRepository.findById(runnableTask.getTaskId())
                    .orElseThrow(() -> new NullPointerException("Task with id: " + runnableTask.getTaskId() +
                            " not found"));
            Long cursor = task.getCursorId();

            if (cursor == null) break;

            TravelInfoUpdaterService updater = travelInfoUpdaterFactory.getTravelInfoUpdaterService(runnableTask);
            try {
                // TODO add annotation for show information about update
                int count = updater.updateTravelInfo(cursor);
                log.info("Updated count: {}, task id: {}, cursor: {}", count, task.getId(), cursor);
            } catch (Exception e) {
                log.error("Error in task: {}, cursor: {}", task.getId(), cursor);
                UpdaterErrorHandlerService handler = updaterErrorHandlerFactory.getUpdaterErrorHandlerService(runnableTask);
                handler.handleError(e, runnableTask);
            }

            CursorService cursorService = cursorFactory.getCursorService(runnableTask);
            Long nextCursor = cursorService.getNextCursorId(cursor);
            task.setCursorId(nextCursor);

            infoTaskRepository.save(task);
        }
    }
}
