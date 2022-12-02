package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.repository.TutuRouteRepository;
import com.summerdev.travelstoragemanager.service.api.tutu.TutuApiService;
import com.summerdev.travelstoragemanager.service.converter.TrainInfoConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
    private TrainInfoConverter trainInfoConverter;
    @Mock
    private TrainInfoService trainsInfoService;

    @Test
    void updateTravelInfoError() {
        long testId = 1;
        when(tutuRouteRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(NullPointerException.class, () ->
                trainInfoUpdaterService.updateTravelInfo(testId));

        assertEquals("Route with id: " + testId + " not found", exception.getMessage());
    }
}