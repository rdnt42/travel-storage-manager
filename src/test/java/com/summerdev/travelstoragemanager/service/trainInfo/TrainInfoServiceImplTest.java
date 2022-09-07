package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import com.summerdev.travelstoragemanager.repository.TrainInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 07.09.2022
 * Time: 22:05
 */
@ExtendWith(MockitoExtension.class)
class TrainInfoServiceImplTest {
    @InjectMocks
    private TrainInfoServiceImpl trainInfoService;

    @Mock
    private TrainInfoRepository trainInfoRepository;

    @Test
    void updateOrCreateEmptyEntitiesError() {
        when(trainInfoRepository.saveAll(Collections.emptyList()))
                .thenThrow(IllegalArgumentException.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                trainInfoService.updateOrCreate(Collections.emptyList()));

        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    void updateOrCreateSuccess() {
        when(trainInfoRepository.saveAll(anyCollection()))
                .then(AdditionalAnswers.returnsFirstArg());
        when(trainInfoRepository.findDistinctTrain(
                anyLong(), anyLong(), anyString()))
                .thenReturn(Optional.of(new TrainInfo()));

        int resultCount = trainInfoService.updateOrCreate(getTwoDiffInfos());

        assertEquals(2, resultCount);
    }

    @Test
    void updateOrCreateSuccessFiltered() {
        when(trainInfoRepository.saveAll(anyCollection()))
                .then(AdditionalAnswers.returnsFirstArg());
        when(trainInfoRepository.findDistinctTrain(
                anyLong(), anyLong(), anyString()))
                .thenReturn(Optional.of(new TrainInfo()));

        int resultCount = trainInfoService.updateOrCreate(getTwoEqualsInfos());

        assertEquals(1, resultCount);
    }

    private List<TrainInfo> getTwoDiffInfos() {
        TrainInfo trainInfo1 = TrainInfo
                .builder()
                .arrivalCity(getGeoNameData(1))
                .departureCity(getGeoNameData(2))
                .trainNumber("diff1")
                .build();

        TrainInfo trainInfo2 = TrainInfo
                .builder()
                .arrivalCity(getGeoNameData(1))
                .departureCity(getGeoNameData(2))
                .trainNumber("diff2")
                .build();

        return List.of(trainInfo1, trainInfo2);
    }

    private List<TrainInfo> getTwoEqualsInfos() {
        TrainInfo trainInfo1 = TrainInfo
                .builder()
                .arrivalCity(getGeoNameData(1))
                .departureCity(getGeoNameData(2))
                .trainNumber("eq")
                .build();

        TrainInfo trainInfo2 = TrainInfo
                .builder()
                .arrivalCity(getGeoNameData(1))
                .departureCity(getGeoNameData(2))
                .trainNumber("eq")
                .build();

        return List.of(trainInfo1, trainInfo2);
    }

    private GeoNameData getGeoNameData(long id) {
        GeoNameData data = new GeoNameData();
        data.setId(id);

        return data;
    }
}