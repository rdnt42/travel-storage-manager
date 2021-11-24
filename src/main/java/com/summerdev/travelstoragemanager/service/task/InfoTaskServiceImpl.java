package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.entity.TaskType;
import com.summerdev.travelstoragemanager.entity.TaskType.TaskTypes;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.repository.TaskTypeRepository;
import com.summerdev.travelstoragemanager.service.task.factory.RunnableTask;
import com.summerdev.travelstoragemanager.service.task.factory.TaskFactory;
import com.summerdev.travelstoragemanager.service.travelInfo.CursorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@Service
@RequiredArgsConstructor
public class InfoTaskServiceImpl implements InfoTaskService {

    protected static final Map<Long, RunnableTask> infoTasksMap = new HashMap<>();

    @NonNull
    private final TaskFactory taskFactory;

    @NonNull
    private final TaskTypeRepository taskTypeRepository;

    @NonNull
    private final InfoTaskRepository infoTaskRepository;


    @Override
    @Transactional
    public InfoTask createTask(TaskTypes taskTypeEnum) {
        InfoTask newTask = createInitInfoTask(taskTypeEnum);
        infoTaskRepository.save(newTask);

        RunnableTask runnableTask = taskFactory.getTask(newTask, taskTypeEnum);
        infoTasksMap.put(newTask.getId(), runnableTask);

        return newTask;
    }

    private InfoTask createInitInfoTask(TaskTypes taskTypeEnum) {
        TaskType taskType = taskTypeRepository.findById(taskTypeEnum.getIdValue())
                .orElseThrow(() -> new NullPointerException("Task type with id: " + taskTypeEnum.getIdValue() +
                        " not found"));

        InfoTask task = new InfoTask();
        task.setTaskType(taskType);

        CursorService infoService = taskFactory.getCursorService(taskTypeEnum);
        task.setCursorId(infoService.getFirstCursorId());

        return task;
    }


}
