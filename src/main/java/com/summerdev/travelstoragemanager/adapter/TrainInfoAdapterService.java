package com.summerdev.travelstoragemanager.adapter;

import com.summerdev.travelstoragemanager.entity.SeatType.SeatTypeEnum;
import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import com.summerdev.travelstoragemanager.entity.train.TrainPrice;
import com.summerdev.travelstoragemanager.entity.train.TutuStation;
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

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 15.12.2021
 * Time: 23:45
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TrainInfoAdapterService {
    @NonNull TutuStationRepository tutuStationRepository;

    public List<TrainInfo> getTrainInfos(TutuTrainsResponse response) {
        List<TrainInfo> trainInfos = new ArrayList<>();
        for (TutuTripItemResponse trip : response.getTrips()) {
            TrainInfo trainIfo = convertResponseToTrainInfo(trip);
            if (trainIfo == null) continue;

            List<TrainPrice> trainPrices = convertResponseToTrainPrice(trip.getCategories());
            trainIfo.addNewPrices(trainPrices);

            trainInfos.add(trainIfo);
        }

        return trainInfos;
    }

    private TrainInfo convertResponseToTrainInfo(TutuTripItemResponse trip) {
        TutuStation arrivalStation = tutuStationRepository.findById(trip.getArrivalStation())
                .orElse(null);
        TutuStation departureStation = tutuStationRepository.findById(trip.getDepartureStation())
                .orElse(null);

        if (arrivalStation == null || departureStation == null) return null;

        return TrainInfo.builder()
                .travelTime(trip.getTravelTimeInSeconds())
                .departureCity(departureStation.getGeoName())
                .arrivalCity(arrivalStation.getGeoName())
                .trainNumber(trip.getTrainNumber())
                .build();
    }

    private List<TrainPrice> convertResponseToTrainPrice(List<TutuRailwayCarriageResponse> categories) {
        List<TrainPrice> trainPrices = new ArrayList<>();
        for (TutuRailwayCarriageResponse category : categories) {
            SeatTypeEnum seatType = SeatTypeEnum.getByDsc(category.getType());
            TrainPrice price = new TrainPrice(category.getPrice(), getComfortType(seatType), seatType.getId());
            trainPrices.add(price);
        }

        return trainPrices;
    }

    private ComfortType getComfortType(SeatTypeEnum seatType) {
        switch (seatType) {
            case SEAT_TYPE_ID_COUPE:
                return ComfortType.COMFORT_TYPE_COMFORT;

            case SEAT_TYPE_ID_LUX:
            case SEAT_TYPE_ID_SOFT:
                return ComfortType.COMFORT_TYPE_LUXURY;

            case SEAT_TYPE_ID_ECONOMY:
            case SEAT_TYPE_ID_SEDENTARY:

            default:
                return ComfortType.COMFORT_TYPE_CHEAP;
        }
    }
}

