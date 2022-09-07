package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.adapter.TrainInfoAdapterService;
import com.summerdev.travelstoragemanager.repository.TutuRouteRepository;
import com.summerdev.travelstoragemanager.service.api.tutu.TutuApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 07.09.2022
 * Time: 21:58
 */
@ExtendWith(MockitoExtension.class)
class TrainInfoUpdaterServiceImplTest {
    @InjectMocks
    private TrainInfoUpdaterServiceImpl trainInfoUpdaterService;

    @Mock
    private TutuRouteRepository tutuRouteRepository;
    @Mock
    private TutuApiService tutuApiService;
    @Mock
    private TrainInfoAdapterService trainInfoAdapterService;
    @Mock
    private TrainInfoService trainsInfoService;


    @Test
    void updateTravelInfoError() {
        Exception exception = assertThrows(NullPointerException.class, () ->
                trainInfoUpdaterService.updateTravelInfo(1L));

        assertEquals("Route with id: 1 not found", exception.getMessage());
    }
}