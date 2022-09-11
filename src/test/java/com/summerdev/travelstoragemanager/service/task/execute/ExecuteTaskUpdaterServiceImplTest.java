package com.summerdev.travelstoragemanager.service.task.execute;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.TravelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.UpdaterErrorHandlerService;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.storage.ServiceTypeServiceStorage;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.06.2022
 * Time: 23:53
 */
@ExtendWith(MockitoExtension.class)
class ExecuteTaskUpdaterServiceImplTest {

    @InjectMocks
    private ExecuteTaskUpdaterServiceImpl executeTaskUpdaterService;

    @Mock
    private ServiceTypeServiceStorage<TravelInfoUpdaterService> travelInfoUpdaterServiceStorage;
    @Mock
    private ServiceTypeServiceStorage<UpdaterErrorHandlerService> updaterErrorServiceStorage;
    @Mock
    private ServiceTypeServiceStorage<CursorService> cursorServiceStorage;

    @Mock
    private TravelInfoUpdaterService travelInfoUpdaterService;
    @Mock
    private UpdaterErrorHandlerService executeTaskErrorHandlerService;
    @Mock
    private CursorService cursorService;

    @Mock
    private InfoTaskRepository infoTaskRepository;

    @Mock
    private RunnableTask task;

    @BeforeEach
    void setUp() {
        executeTaskUpdaterService = new ExecuteTaskUpdaterServiceImpl(
                travelInfoUpdaterServiceStorage, updaterErrorServiceStorage, cursorServiceStorage, infoTaskRepository);
    }
    @Test
    void updateTravelInfoSuccess() {
        initInfoUpdater();
        InfoTask infoTask = getInfoTask();
        when(travelInfoUpdaterService.updateTravelInfo(infoTask.getCursorId()))
                .thenReturn(10);

        int count = executeTaskUpdaterService.updateTravelInfo(task, infoTask);

        Assertions.assertEquals(10, count);
    }

    @Test
    void updateTravelInfoSuccessOnError() {
        initInfoUpdater();
        InfoTask infoTask = getInfoTask();
        when(travelInfoUpdaterService.updateTravelInfo(infoTask.getCursorId()))
                .thenThrow(RuntimeException.class);

        initErrorHandler();
        doNothing().when(executeTaskErrorHandlerService).handleError(any(), any());

        int count = executeTaskUpdaterService.updateTravelInfo(task, infoTask);

        Assertions.assertEquals(0, count);
    }

    @Test
    void updateNextCursorSuccess() {
        long expectedCursor = 200L;
        long defaultCursor = 1L;

        initCursor();
        when(cursorService.getNextCursorId(anyLong()))
                .thenReturn(expectedCursor);

        InfoTask task = getInfoTask();
        task.setCursorId(defaultCursor);

        when(infoTaskRepository.save(task))
                .thenReturn(task);

        executeTaskUpdaterService.updateNextCursor(getRunnable(), task);
        Assertions.assertEquals(expectedCursor, task.getCursorId());
    }

    private void initInfoUpdater() {
        when(travelInfoUpdaterServiceStorage.getService(any()))
                .thenReturn(travelInfoUpdaterService);
    }

    private void initErrorHandler() {
        when(updaterErrorServiceStorage.getService(any()))
                .thenReturn(executeTaskErrorHandlerService);
    }

    private void initCursor() {
        when(cursorServiceStorage.getService(any()))
                .thenReturn(cursorService);
    }

    private RunnableTask getRunnable() {
        return Mockito.mock(RunnableTask.class);
    }

    private InfoTask getInfoTask() {
        EasyRandom random = new EasyRandom();
        return random.nextObject(InfoTask.class);
    }
}