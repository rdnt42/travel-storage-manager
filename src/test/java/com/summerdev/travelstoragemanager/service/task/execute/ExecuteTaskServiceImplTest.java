package com.summerdev.travelstoragemanager.service.task.execute;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.serviceType.ServiceType;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 06.07.2022
 * Time: 23:26
 */
@ExtendWith(MockitoExtension.class)
class ExecuteTaskServiceImplTest {

    @InjectMocks
    private ExecuteTaskServiceImpl executeTaskService;

    @Mock
    private InfoTaskRepository infoTaskRepository;

    @Mock
    private ExecuteTaskUpdaterService executeTaskUpdaterService;

    private RunnableTask runnableTask;

    @BeforeEach
    private void updateTask() {
        runnableTask = new RunnableTask() {
            @Override
            public Class<? extends ServiceType> getServiceTypeClass() {
                return null;
            }

            @Override
            public void run() {

            }
        };

        ScheduledFuture<?> future = Mockito.mock(ScheduledFuture.class);
        runnableTask.setFuture(future);
        runnableTask.setTaskId(1L);
    }

    @Test
    void executeTask() {
        Long taskId = 1L;
        Optional<InfoTask> task = getTaskFinishedTask(taskId);

        when(infoTaskRepository.findById(taskId))
                .thenReturn(task);

        executeTaskService.executeTask(runnableTask);

        verify(infoTaskRepository, times(1))
                .findById(taskId);

        verify(executeTaskUpdaterService, times(0))
                .updateTravelInfo(runnableTask, task.orElse(null));

        verify(executeTaskUpdaterService, times(0))
                .updateNextCursor(runnableTask, task.orElse(null));
    }

    private Optional<InfoTask> getTaskFinishedTask(Long id) {
        EasyRandom random = new EasyRandom();
        InfoTask task = random.nextObject(InfoTask.class);
        task.setId(id);
        task.setCursorId(null);

        return Optional.of(task);
    }
}