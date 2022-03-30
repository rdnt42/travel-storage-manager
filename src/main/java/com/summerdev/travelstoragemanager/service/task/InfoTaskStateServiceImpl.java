package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.service.ThreadPoolTaskService;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.summerdev.travelstoragemanager.service.task.InfoTaskServiceImpl.infoTasksMap;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 23:48
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class InfoTaskStateServiceImpl implements InfoTaskStateService {
    private static final String NOT_FOUND_MESSAGE = "Task not found. Id: ";

    @NonNull InfoTaskRepository infoTaskRepository;
    @NonNull ThreadPoolTaskService threadPoolTaskService;

    @Override
    public void enableTask(InfoTask task) {
        RunnableTask runnableTask = infoTasksMap.get(task.getId());
        if (runnableTask == null) {
            throw new NullPointerException(NOT_FOUND_MESSAGE+ task.getId());
        }

        threadPoolTaskService.startTask(runnableTask);
    }

    @Override
    public void disableTask(InfoTask task) {
        RunnableTask runnableTask = infoTasksMap.get(task.getId());
        if (runnableTask == null) {
            throw new NullPointerException(NOT_FOUND_MESSAGE + task.getId());
        }

        threadPoolTaskService.stopTask(runnableTask);
    }

    @Override
    public void disableAndDeleteTask(Long taskId) {
        InfoTask task = infoTaskRepository.findById(taskId)
                .orElseThrow(() -> new NullPointerException(NOT_FOUND_MESSAGE + taskId));

        disableTask(task);
        infoTaskRepository.delete(task);
    }

}
