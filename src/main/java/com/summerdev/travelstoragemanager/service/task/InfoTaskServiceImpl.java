package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.entity.TaskType;
import com.summerdev.travelstoragemanager.entity.TaskType.TaskTypes;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.repository.TaskTypeRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.factory.CursorFactory;
import com.summerdev.travelstoragemanager.service.factory.TaskFactory;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:08
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class InfoTaskServiceImpl implements InfoTaskService {

    protected static final Map<Long, RunnableTask> infoTasksMap = new HashMap<>();

    @NonNull TaskFactory taskFactory;
    @NonNull CursorFactory cursorFactory;

    @NonNull TaskTypeRepository taskTypeRepository;
    @NonNull InfoTaskRepository infoTaskRepository;

    @Override
    @Transactional
    public InfoTask createTask(TaskTypes taskTypeEnum) {
        //TODO rework cycle dependency Runnable <-> InfoTask
        InfoTask newTask = createInitInfoTask(taskTypeEnum);

        RunnableTask runnableTask = taskFactory.getTask(taskTypeEnum);
        CursorService cursorService = cursorFactory.getCursorService(runnableTask);
        newTask.setCursorId(cursorService.getFirstCursorId());

        infoTaskRepository.save(newTask);

        runnableTask.setTaskId(newTask.getId());
        infoTasksMap.put(newTask.getId(), runnableTask);

        return newTask;
    }

    private InfoTask createInitInfoTask(TaskTypes taskTypeEnum) {
        TaskType taskType = taskTypeRepository.findById(taskTypeEnum.getIdValue())
                .orElseThrow(() -> new NullPointerException("Task type with id: " + taskTypeEnum.getIdValue() +
                        " not found"));

        InfoTask task = new InfoTask();
        task.setTaskType(taskType);

        return task;
    }


}
