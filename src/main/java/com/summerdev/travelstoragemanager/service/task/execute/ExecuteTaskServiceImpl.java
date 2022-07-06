package com.summerdev.travelstoragemanager.service.task.execute;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 09.01.2022
 * Time: 21:02
 */
@RequiredArgsConstructor
@Service
public class ExecuteTaskServiceImpl implements ExecuteTaskService {
    private final InfoTaskRepository infoTaskRepository;
    private final ExecuteTaskUpdaterService executeTaskUpdaterService;

    @Override
    public void executeTask(RunnableTask runnableTask) {
        while (!runnableTask.getFuture().isCancelled()) {
            InfoTask task = infoTaskRepository.findById(runnableTask.getTaskId())
                    .orElseThrow(() -> new NullPointerException("Task with id: " + runnableTask.getTaskId() +
                            " not found"));
            Long cursor = task.getCursorId();
            if (cursor == null) break;

            executeTaskUpdaterService.updateTravelInfo(runnableTask, task);
            executeTaskUpdaterService.updateNextCursor(runnableTask, task);
        }
    }
}
