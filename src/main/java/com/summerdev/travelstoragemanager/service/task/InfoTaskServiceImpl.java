package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.entity.directory.TaskType;
import com.summerdev.travelstoragemanager.enums.TaskTypes;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.repository.TaskTypeRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.ThreadPoolTaskService;
import com.summerdev.travelstoragemanager.service.factory.RunnableTaskFactory;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.storage.ServiceTypeServiceStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:08
 */
@RequiredArgsConstructor
@Service
public class InfoTaskServiceImpl implements InfoTaskService {
    protected static final Map<Long, RunnableTask> infoTasksMap = new ConcurrentHashMap<>();

    private final RunnableTaskFactory runnableTaskFactory;
    private final ServiceTypeServiceStorage<CursorService> cursorServiceStorage;


    private final TaskTypeRepository taskTypeRepository;
    private final InfoTaskRepository infoTaskRepository;

    private final ThreadPoolTaskService threadPoolTaskService;

    @Override
    @Transactional
    public InfoTask createTask(TaskTypes taskTypeEnum) {
        InfoTask newTask = createInitInfoTask(taskTypeEnum);

        RunnableTask runnableTask = runnableTaskFactory.getTask(taskTypeEnum);
        CursorService cursorService = cursorServiceStorage.getService(runnableTask.getServiceTypeClass());
        newTask.setCursorId(cursorService.getFirstCursorId());

        newTask = infoTaskRepository.save(newTask);

        runnableTask.setTaskId(newTask.getId());
        infoTasksMap.put(newTask.getId(), runnableTask);

        return newTask;
    }

    @Override
    public void initTasks() {
        List<InfoTask> taskList = infoTaskRepository.findAll();

        for (InfoTask infoTask : taskList) {
            TaskTypes taskType = TaskTypes.getById(infoTask.getTaskTypeId());
            RunnableTask runnableTask = runnableTaskFactory.getTask(taskType);

            runnableTask.setTaskId(infoTask.getId());
            infoTasksMap.put(infoTask.getId(), runnableTask);
        }

        infoTasksMap.values().forEach(threadPoolTaskService::startTaskWithShortInitDelay);
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
