package com.summerdev.travelstoragemanager.service.task.execute;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.service.TravelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.UpdaterErrorHandlerService;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;
import com.summerdev.travelstoragemanager.storage.ServiceTypeServiceStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    private TravelInfoUpdaterService travelInfoUpdaterService;

    @Mock
    private RunnableTask task;

    @Test
    void updateTravelInfoSuccess() {
        when(travelInfoUpdaterServiceStorage.getService(any()))
                .thenReturn(travelInfoUpdaterService);
        when(travelInfoUpdaterService.updateTravelInfo(any()))
                .thenReturn(10);

        int count = executeTaskUpdaterService.updateTravelInfo(task, 1L, 1L);

        Assertions.assertEquals(10, count);
    }

}