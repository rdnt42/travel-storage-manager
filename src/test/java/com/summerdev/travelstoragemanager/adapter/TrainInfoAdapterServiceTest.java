package com.summerdev.travelstoragemanager.adapter;

import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import com.summerdev.travelstoragemanager.entity.train.TrainPrice;
import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuRailwayCarriageResponse;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;
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

import static com.summerdev.travelstoragemanager.entity.SeatType.SeatTypeEnum.*;
import static com.summerdev.travelstoragemanager.entity.directory.ComfortType.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

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
    void convertResponsesEmptyResponsesSuccess() {
        List<TrainInfo> trainInfos = trainInfoAdapterService.convertResponsesToTrainsInfo(new TutuTrainsResponse());

        assertTrue(trainInfos.isEmpty());
    }

    @Test
    void convertResponsesTenObjectSuccess() {
        Mockito.when(tutuStationRepository.findById(anyLong()))
                .thenReturn(getEmptyStation());

        TutuTrainsResponse response = getFillTutuTrainsWithTenItems();

        List<TrainInfo> trainInfos = trainInfoAdapterService.convertResponsesToTrainsInfo(response);

        assertEquals(10, trainInfos.size());
    }

    @Test
    void convertResponsesTenObjectsWithTwoInvalidSuccess() {
        Mockito.when(tutuStationRepository.findById(anyLong()))
                .thenReturn(getEmptyStation());

        TutuTrainsResponse response = getFillTutuTrainsWithTenItems();
        response.getTrips().get(0).setDepartureStation(null);
        response.getTrips().get(1).setArrivalStation(null);

        List<TrainInfo> trainInfos = trainInfoAdapterService.convertResponsesToTrainsInfo(response);

        assertEquals(8, trainInfos.size());
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
    void convertResponseEconomyAndSeat() {
        Mockito.when(tutuStationRepository.findById(any()))
                .thenReturn(getEmptyStation());

        TutuTripItemResponse response = getFillTripObjectResponse(SEAT_TYPE_ID_ECONOMY.getDsc(), 2);
        response.getCategories().get(1).setType(SEAT_TYPE_ID_SEDENTARY.getDsc());

        List<TrainPrice> trainPrices = trainInfoAdapterService.convertResponseToTrainInfo(response)
                .getTrainPrices();

        assertEquals(2, trainPrices.size());

        ComfortType comfortType = trainPrices.get(0).getComfortType();
        assertEquals(COMFORT_TYPE_CHEAP, comfortType);

        comfortType = trainPrices.get(1).getComfortType();
        assertEquals(COMFORT_TYPE_CHEAP, comfortType);
    }

    @Test
    void convertResponseCoupe() {
        Mockito.when(tutuStationRepository.findById(any()))
                .thenReturn(getEmptyStation());

        TutuTripItemResponse response = getFillTripObjectResponse(SEAT_TYPE_ID_COUPE.getDsc(), 1);

        List<TrainPrice> trainPrices = trainInfoAdapterService.convertResponseToTrainInfo(response)
                .getTrainPrices();
        ComfortType comfortType = trainPrices.get(0).getComfortType();

        assertEquals(1, trainPrices.size());
        assertEquals(COMFORT_TYPE_COMFORT, comfortType);
    }

    @Test
    void convertResponseLuxAndSoft() {
        Mockito.when(tutuStationRepository.findById(any()))
                .thenReturn(getEmptyStation());

        TutuTripItemResponse response = getFillTripObjectResponse(SEAT_TYPE_ID_LUX.getDsc(), 2);
        response.getCategories().get(1).setType(SEAT_TYPE_ID_SOFT.getDsc());

        List<TrainPrice> trainPrices = trainInfoAdapterService.convertResponseToTrainInfo(response)
                .getTrainPrices();

        assertEquals(2, trainPrices.size());

        ComfortType comfortType = trainPrices.get(0).getComfortType();
        assertEquals(COMFORT_TYPE_LUXURY, comfortType);

        comfortType = trainPrices.get(1).getComfortType();
        assertEquals(COMFORT_TYPE_LUXURY, comfortType);
    }

    @Test
    void convertResponseWithOneCorrectSeatType() {
        Mockito.when(tutuStationRepository.findById(any()))
                .thenReturn(getEmptyStation());

        TutuTripItemResponse response = getFillTripObjectResponse("incorrect", 10);
        response.getCategories().get(1).setType(SEAT_TYPE_ID_COUPE.getDsc());

        assertEquals("incorrect", response.getCategories().get(0).getType());

        List<TrainPrice> trainPrices = trainInfoAdapterService.convertResponseToTrainInfo(response)
                .getTrainPrices();

        ComfortType comfortType = trainPrices.get(0).getComfortType();

        assertEquals(ComfortType.COMFORT_TYPE_COMFORT, comfortType);
        assertEquals(1, trainPrices.size());
    }

    private Optional<TutuStation> getEmptyStation() {
        TutuStation station = new TutuStation();

        return Optional.of(station);
    }

    private TutuTripItemResponse getFillTripObjectResponse() {
        return TutuTripItemResponse.builder()
                .departureStation(1L)
                .arrivalStation(2L)
                .categories(getFillCategories(SEAT_TYPE_ID_COUPE.getDsc(), 10))
                .build();
    }

    private TutuTripItemResponse getFillTripObjectResponse(String seatTypeName, int countCategories) {
        return TutuTripItemResponse.builder()
                .departureStation(1L)
                .arrivalStation(2L)
                .categories(getFillCategories(seatTypeName, countCategories))
                .build();
    }

    private List<TutuRailwayCarriageResponse> getFillCategories(String seatTypeName, int countCategories) {
        List<TutuRailwayCarriageResponse> responses = new ArrayList<>();
        for (int i = 0; i < countCategories; i++) {
            TutuRailwayCarriageResponse response = new TutuRailwayCarriageResponse();
            response.setPrice(i);
            response.setType(seatTypeName);

            responses.add(response);
        }

        return responses;
    }

    private TutuTrainsResponse getFillTutuTrainsWithTenItems() {
        List<TutuTripItemResponse> responses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TutuTripItemResponse response = getFillTripObjectResponse();
            responses.add(response);
        }

        return new TutuTrainsResponse(responses);
    }

}