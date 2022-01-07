package com.summerdev.travelstoragemanager.decode;

import com.summerdev.travelstoragemanager.entity.SeatType.SeatTypes;
import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import com.summerdev.travelstoragemanager.entity.tutu.TrainInfo;
import com.summerdev.travelstoragemanager.entity.tutu.TrainPrice;
import com.summerdev.travelstoragemanager.entity.tutu.TutuStation;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuRailwayCarriageResponse;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTripItemResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.summerdev.travelstoragemanager.entity.SeatType.SeatTypes.SEAT_TYPE_ID_COMMON;
import static com.summerdev.travelstoragemanager.entity.SeatType.SeatTypes.SEAT_TYPE_ID_COUPE;
import static com.summerdev.travelstoragemanager.entity.SeatType.SeatTypes.SEAT_TYPE_ID_ECONOMY;
import static com.summerdev.travelstoragemanager.entity.SeatType.SeatTypes.SEAT_TYPE_ID_LUX;
import static com.summerdev.travelstoragemanager.entity.SeatType.SeatTypes.SEAT_TYPE_ID_SEDENTARY;
import static com.summerdev.travelstoragemanager.entity.SeatType.SeatTypes.SEAT_TYPE_ID_SOFT;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 15.12.2021
 * Time: 23:45
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TrainInfoDecodeService {
    @NonNull TutuStationRepository tutuStationRepository;

    public List<TrainInfo> decodeTrainsResponse(TutuTrainsResponse response) {
        List<TrainInfo> trainInfos = new ArrayList<>();
        for (TutuTripItemResponse trip : response.getTrips()) {
            TrainInfo trainIfo = getTrainInfo(trip);
            if (trainIfo == null) continue;

            List<TrainPrice> trainPrices = getPrices(trip.getCategories());
            trainIfo.addNewPrices(trainPrices);

            trainInfos.add(trainIfo);
        }

        return trainInfos;
    }

    private TrainInfo getTrainInfo(TutuTripItemResponse trip) {
        TrainInfo trainIfo = new TrainInfo();
        trainIfo.setTravelTime(trip.getTravelTimeInSeconds());

        TutuStation arrivalStation = tutuStationRepository.findById(trip.getArrivalStation())
                .orElse(null);
        TutuStation departureStation = tutuStationRepository.findById(trip.getDepartureStation())
                .orElse(null);

        if (arrivalStation == null || departureStation == null) return null;

        trainIfo.setDepartureCity(departureStation.getGeoName());
        trainIfo.setArrivalCity(arrivalStation.getGeoName());
        trainIfo.setTrainNumber(trip.getTrainNumber());

        return trainIfo;
    }

    private List<TrainPrice> getPrices(List<TutuRailwayCarriageResponse> categories) {
        List<TrainPrice> trainPrices = new ArrayList<>();
        for (TutuRailwayCarriageResponse category : categories) {
            SeatTypes seatType = getSeatType(category.getType());
            TrainPrice price = new TrainPrice(category.getPrice(), getComfortType(seatType));
            trainPrices.add(price);
        }

        return trainPrices;
    }

    private ComfortType getComfortType(SeatTypes seatType) {
        switch (seatType) {
            case SEAT_TYPE_ID_COUPE:
                return ComfortType.COMFORT_TYPE_COMFORT;

            case SEAT_TYPE_ID_LUX:
            case SEAT_TYPE_ID_SOFT:
                return ComfortType.COMFORT_TYPE_LUXURY;

            case SEAT_TYPE_ID_COMMON:
            case SEAT_TYPE_ID_ECONOMY:
            case SEAT_TYPE_ID_SEDENTARY:

            default:
                return ComfortType.COMFORT_TYPE_CHEAP;
        }
    }

    private SeatTypes getSeatType(String category) {
        switch (category) {
            case "plazcard":
                return SEAT_TYPE_ID_ECONOMY;
            case "coupe":
                return SEAT_TYPE_ID_COUPE;
            case "sedentary":
                return SEAT_TYPE_ID_SEDENTARY;
            case "lux":
                return SEAT_TYPE_ID_LUX;
            case "soft":
                return SEAT_TYPE_ID_SOFT;
            default:
                return SEAT_TYPE_ID_COMMON;
        }
    }

}

