package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.entity.directory.TaskType;
import com.summerdev.travelstoragemanager.enums.TaskTypes;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.repository.TaskTypeRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.factory.RunnableTaskFactory;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.storage.ServiceTypeServiceStorage;
import com.summerdev.travelstoragemanager.utils.EasyRandomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static com.summerdev.travelstoragemanager.enums.TaskTypes.TASK_GET_HOTELS_INFO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 10.07.2022
 * Time: 14:45
 */
@ExtendWith(MockitoExtension.class)
class InfoTaskServiceImplTest {
    private final EasyRandomService easyRandomService = new EasyRandomService();

    @InjectMocks
    private InfoTaskServiceImpl infoTaskService;

    @Mock
    private TaskTypeRepository taskTypeRepository;
    @Mock
    private InfoTaskRepository infoTaskRepository;

    @Mock
    private RunnableTaskFactory runnableTaskFactory;
    @Mock
    private ServiceTypeServiceStorage<CursorService> cursorServiceStorage;
    @Mock
    private CursorService cursorService;

    @Test
    void createTaskSuccess() {
        Long cursorId = 5L;
        Long taskId = 100L;

        TaskTypes taskType = TASK_GET_HOTELS_INFO;
        TaskType type = getTaskType(taskType);

        initRepos(type, taskType);
        initCursor(cursorId);

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((InfoTask)args[0]).setId(taskId);
            return args[0];
        }).when(infoTaskRepository).save(any());

        InfoTask result = infoTaskService.createTask(taskType);

        assertEquals(cursorId, result.getCursorId());
        assertEquals(type, result.getTaskType());

        Map<Long, RunnableTask> infoTasksMap = InfoTaskServiceImpl.infoTasksMap;
        assertFalse(infoTasksMap.isEmpty());
        assertNotNull(infoTasksMap.get(taskId));
    }


    private void initRepos(TaskType type, TaskTypes taskType) {
        RunnableTask runnableTask = mock(RunnableTask.class);
        when(taskTypeRepository.findById(taskType.getIdValue()))
                .thenReturn(Optional.of(type));
        when(runnableTaskFactory.getTask(taskType))
                .thenReturn(runnableTask);
    }

    private void initCursor(Long cursorId) {
        when(cursorServiceStorage.getService(any()))
                .thenReturn(cursorService);
        when(cursorService.getFirstCursorId())
                .thenReturn(cursorId);
    }

    private TaskType getTaskType(TaskTypes typeEnum) {
        TaskType taskType = new TaskType();
        taskType.setId(typeEnum.getIdValue());

        return taskType;
    }
}