package com.summerdev.travelstoragemanager.service.task.execute;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Mock
    private RunnableTask runnableTask;
    @Mock
    private ScheduledFuture<?> future;

    @Test
    void executeTaskNoExecute() {
        Long taskId = 1L;

        initFuture(true);

        executeTaskService.executeTask(runnableTask);

        verify(infoTaskRepository, times(0))
                .findById(taskId);

        verify(executeTaskUpdaterService, times(0))
                .updateTravelInfo(eq(runnableTask), any(InfoTask.class));

        verify(executeTaskUpdaterService, times(0))
                .updateNextCursor(eq(runnableTask), any(InfoTask.class));
    }

    @Test
    void executeTaskSuccess() {
        Long taskId = 1L;
        Optional<InfoTask> task = getTaskFinishedTask(taskId);

        initFuture(false);
        when(runnableTask.getTaskId())
                .thenReturn(taskId);
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

    @Test
    void executeTaskError() {
        Long taskId = 1L;

        initFuture(false);
        when(runnableTask.getTaskId())
                .thenReturn(taskId);
        when(infoTaskRepository.findById(taskId))
                .thenThrow(NullPointerException.class);

        Exception exception = assertThrows(NullPointerException.class, () ->
                executeTaskService.executeTask(runnableTask));

        assertNotNull(exception);
    }

    private Optional<InfoTask> getTaskFinishedTask(Long id) {
        EasyRandom random = new EasyRandom();
        InfoTask task = random.nextObject(InfoTask.class);
        task.setId(id);
        task.setCursorId(null);

        return Optional.of(task);
    }

    private void initFuture(boolean isCancel) {
        doReturn(future)
                .when(runnableTask).getFuture();
        when(runnableTask.getFuture().isCancelled())
                .thenReturn(isCancel);
    }
}