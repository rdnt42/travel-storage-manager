package com.summerdev.travelstoragemanager.adapter;

import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuRailwayCarriageResponse;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTripItemResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.summerdev.travelstoragemanager.entity.SeatType.SeatTypeEnum.SEAT_TYPE_ID_COUPE;
import static com.summerdev.travelstoragemanager.entity.directory.ComfortType.COMFORT_TYPE_COMFORT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 25.05.2022
 * Time: 23:29
 */
@ExtendWith(MockitoExtension.class)
class TrainInfoAdapterServiceTest {

    private TrainInfoAdapterService trainInfoAdapterService;

    @Mock
    private TutuStationRepository tutuStationRepository;

    @BeforeEach
    void setUp() {
        trainInfoAdapterService = new TrainInfoAdapterService(tutuStationRepository);
    }

    @Test
    void convertResponseWithEmptyArrivalFailed() {
        TutuTripItemResponse response = getFillTripObjectResponse();
        response.setArrivalStation(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                trainInfoAdapterService.convertResponseToTrainInfo(response));

        assertTrue(thrown.getMessage().contains("Arrival station cannot be null"));
    }

    @Test
    void convertResponseWithEmptyDepartureFailed() {
        TutuTripItemResponse response = getFillTripObjectResponse();
        response.setDepartureStation(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                trainInfoAdapterService.convertResponseToTrainInfo(response));

        assertTrue(thrown.getMessage().contains("Arrival station cannot be null"));
    }

    @Test
    void convertResponseCoupe() {
        Mockito.when(tutuStationRepository.findById(any()))
                .thenReturn(getEmptyStation());

        TutuTripItemResponse response = getFillTripObjectResponse(SEAT_TYPE_ID_COUPE.getDsc());

        TrainInfo trainInfo = trainInfoAdapterService.convertResponseToTrainInfo(response);
        ComfortType comfortType = trainInfo.getTrainPrices().get(0).getComfortType();

        assertEquals(COMFORT_TYPE_COMFORT, comfortType);
    }

    @Test
    void convertResponseWithOneCorrectSeatType() {
        Mockito.when(tutuStationRepository.findById(any()))
                .thenReturn(getEmptyStation());

        TutuTripItemResponse response = getFillTripObjectResponse("incorrect");
        response.getCategories().get(1).setType(SEAT_TYPE_ID_COUPE.getDsc());

        assertEquals("incorrect", response.getCategories().get(0).getType());

        TrainInfo trainInfo = trainInfoAdapterService.convertResponseToTrainInfo(response);

        ComfortType comfortType = trainInfo.getTrainPrices().get(0).getComfortType();

        assertEquals(ComfortType.COMFORT_TYPE_COMFORT, comfortType);
        assertEquals(1, trainInfo.getTrainPrices().size());
    }

    private Optional<TutuStation> getEmptyStation() {
        TutuStation station = new TutuStation();

        return Optional.of(station);
    }

    private TutuTripItemResponse getFillTripObjectResponse() {
        return TutuTripItemResponse.builder()
                .departureStation(1L)
                .arrivalStation(2L)
                .categories(getFillCategories(SEAT_TYPE_ID_COUPE.getDsc()))
                .build();
    }

    private TutuTripItemResponse getFillTripObjectResponse(String seatTypeName) {
        return TutuTripItemResponse.builder()
                .departureStation(1L)
                .arrivalStation(2L)
                .categories(getFillCategories(seatTypeName))
                .build();
    }

    private List<TutuRailwayCarriageResponse> getFillCategories(String seatTypeName) {
        List<TutuRailwayCarriageResponse> responses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TutuRailwayCarriageResponse response = new TutuRailwayCarriageResponse();
            response.setPrice(i);
            response.setType(seatTypeName);

            responses.add(response);
        }

        return responses;
    }

}