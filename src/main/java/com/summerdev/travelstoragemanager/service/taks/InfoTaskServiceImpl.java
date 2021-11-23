package com.summerdev.travelstoragemanager.service.taks;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.entity.TaskType;
import com.summerdev.travelstoragemanager.entity.TaskType.TaskTypes;
import com.summerdev.travelstoragemanager.repository.TaskTypeRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private static final Map<Long, RunnableTask> infoTasksMap = new HashMap<>();

    @NonNull
    private final TaskFactory taskFactory;

    @NonNull
    private final TaskTypeRepository taskTypeRepository;

    @Override
    public void enableTask(InfoTask task) {

    }

    @Override
    public void disableTask(InfoTask task) {

    }

    @Override
    public void createTask(TaskTypes taskTypeEnum) {
        TaskType taskType = taskTypeRepository.findById(taskTypeEnum.getIdValue())
                .orElseThrow(() -> new NullPointerException("Task type with id: " + taskTypeEnum.getIdValue() +
                        " not found"));

        InfoTask task = new InfoTask();
        task.setTaskType(taskType);

        Runnable runnableTask = taskFactory.getTask(task, taskTypeEnum);
    }


}
