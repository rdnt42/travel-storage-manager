package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 09.01.2022
 * Time: 21:02
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class ExecuteTaskServiceImpl implements ExecuteTaskService {
    InfoTaskRepository infoTaskRepository;
    ExecuteTaskUpdaterService executeTaskUpdaterService;

    @Override
    public void executeTask(RunnableTask runnableTask) {
        while (!runnableTask.getFuture().isCancelled()) {
            InfoTask task = infoTaskRepository.findById(runnableTask.getTaskId())
                    .orElseThrow(() -> new NullPointerException("Task with id: " + runnableTask.getTaskId() +
                            " not found"));
            Long cursor = task.getCursorId();
            if (cursor == null) break;

            executeTaskUpdaterService.updateTravelInfo(runnableTask, cursor, task.getId());
            executeTaskUpdaterService.updateNextCursor(runnableTask, task, cursor);
        }
    }
}
