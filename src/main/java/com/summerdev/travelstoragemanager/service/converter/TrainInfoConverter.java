package com.summerdev.travelstoragemanager.service.converter;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import com.summerdev.travelstoragemanager.entity.directory.SeatType.SeatTypeEnum;
import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import com.summerdev.travelstoragemanager.entity.train.TrainPrice;
import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.enums.ComfortTypes;
import com.summerdev.travelstoragemanager.repository.ComfortTypeRepository;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuRailwayCarriageResponse;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTripItemResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 15.12.2021
 * Time: 23:45
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class TrainInfoConverter {
    private final TutuStationRepository tutuStationRepository;
    private final ComfortTypeRepository comfortTypeRepository;

    public List<TrainInfo> convertResponsesToTrainsInfo(TutuTrainsResponse response) {
        return response.getTrips().stream()
                .map(this::tryConvertResponseToTrainInfo)
                .filter(Objects::nonNull)
                .toList();
    }

    private TrainInfo tryConvertResponseToTrainInfo(TutuTripItemResponse trip) {
        try {
            return convertResponseToTrainInfo(trip);
        } catch (Exception e) {
            log.warn("Some unexpected exception when parse Train response, message: {}", e.getMessage());
        }

        return null;
    }

    public TrainInfo convertResponseToTrainInfo(TutuTripItemResponse trip) {
        GeoNameData arrivalCity = getArrivalCityOrThrow(trip.getArrivalStation());
        GeoNameData departureCity = getDepartureCityOrThrow(trip.getDepartureStation());

        TrainInfo trainInfo = getTrainInfoFromBuilder(trip, departureCity, arrivalCity);
        List<TrainPrice> trainPrices = convertCarriageResponsesToTrainPrices(trip.getCategories());

        trainInfo.addNewPrices(trainPrices);

        return trainInfo;
    }

    private GeoNameData getArrivalCityOrThrow(Long arrivalStationId) {
        TutuStation arrivalStation = tutuStationRepository.findById(arrivalStationId)
                .orElseThrow(() -> new IllegalArgumentException("Arrival station cannot be null"));

        return arrivalStation.getGeoName();
    }

    private GeoNameData getDepartureCityOrThrow(Long departureStationId) {
        TutuStation departureStation = tutuStationRepository.findById(departureStationId)
                .orElseThrow(() -> new IllegalArgumentException("Departure station cannot be null"));

        return departureStation.getGeoName();
    }

    private TrainInfo getTrainInfoFromBuilder(TutuTripItemResponse trip, GeoNameData departureCity, GeoNameData arrivalCity) {
        return TrainInfo.builder()
                .travelTime(trip.getTravelTimeInSeconds())
                .departureCity(departureCity)
                .arrivalCity(arrivalCity)
                .trainNumber(trip.getTrainNumber())
                .build();
    }

    private List<TrainPrice> convertCarriageResponsesToTrainPrices(List<TutuRailwayCarriageResponse> categories) {
        return categories.stream()
                .map(this::tryConvertCarriageResponseToTrainPrice)
                .filter(Objects::nonNull)
                .toList();
    }

    private TrainPrice tryConvertCarriageResponseToTrainPrice(TutuRailwayCarriageResponse category) {
        try {
            SeatTypeEnum seatType = getSeatTypeOrThrow(category);

            return new TrainPrice(category.getPrice(), getComfortType(seatType), seatType.getId());
        } catch (Exception e) {
            log.warn("Some unexpected exception when parse Category response, message: {}", e.getMessage());
        }

        return null;
    }

    private SeatTypeEnum getSeatTypeOrThrow(TutuRailwayCarriageResponse category) {
        SeatTypeEnum seatType = SeatTypeEnum.getByDsc(category.getType());
        if (seatType == null) {
            throw new IllegalArgumentException("Category doesn't exist: " + category.getType());
        }

        return seatType;
    }

    private ComfortType getComfortType(SeatTypeEnum seatType) {
        return switch (seatType) {
            case SEAT_TYPE_ID_COUPE -> findComfortType(ComfortTypes.COMFORT_TYPE_COMFORT.getId());
            case SEAT_TYPE_ID_LUX, SEAT_TYPE_ID_SOFT -> findComfortType(ComfortTypes.COMFORT_TYPE_LUXURY.getId());
            case SEAT_TYPE_ID_ECONOMY, SEAT_TYPE_ID_SEDENTARY -> findComfortType(ComfortTypes.COMFORT_TYPE_CHEAP.getId());
        };
    }

    private ComfortType findComfortType(int id) {
        return comfortTypeRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Comfort type with id: " + id + "doesn't exist"));
    }
}

