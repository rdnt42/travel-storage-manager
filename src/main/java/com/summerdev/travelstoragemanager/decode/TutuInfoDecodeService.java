package com.summerdev.travelstoragemanager.decode;

import com.summerdev.travelstoragemanager.entity.TrainInfo;
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

import static com.summerdev.travelstoragemanager.entity.SeatType.SeatTypes.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 15.12.2021
 * Time: 23:45
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TutuInfoDecodeService {
    @NonNull TutuStationRepository tutuStationRepository;

    public List<TrainInfo> decodeTrainsResponse(TutuTrainsResponse response) {

        if (response == null) return new ArrayList<>();

        List<TrainInfo> trainInfos = new ArrayList<>();
        for (TutuTripItemResponse trip : response.getTrips()) {
            for (TutuRailwayCarriageResponse category : trip.getCategories()) {

                TrainInfo trainIfo = getTrainInfo(trip, category);
                if (trainIfo != null) {
                    trainInfos.add(trainIfo);
                }
            }
        }

        return trainInfos;
    }

    private TrainInfo getTrainInfo(TutuTripItemResponse trip, TutuRailwayCarriageResponse category) {
        TrainInfo trainIfo = new TrainInfo();
        trainIfo.setTravelTime(trip.getTravelTimeInSeconds());

        TutuStation arrivalStation = tutuStationRepository.findById(trip.getArrivalStation())
                .orElse(null);
        TutuStation departureStation = tutuStationRepository.findById(trip.getDepartureStation())
                .orElse(null);

        if (arrivalStation == null || departureStation == null) return null;

        trainIfo.setDepartureCity(departureStation.getGeoName());
        trainIfo.setArrivalCity(arrivalStation.getGeoName());

        // TODO rework
        trainIfo.setCost((long) category.getPrice());
        Long seatTypeId = (long)getSeatTypeId(category.getType());
        trainIfo.setSeatTypeId(seatTypeId);

        return trainIfo;
    }

    private int getSeatTypeId(String category) {
        int seatTypeId;
        switch (category) {
            case "plazcard":
                seatTypeId = SEAT_TYPE_ID_ECONOMY.ordinal();
                break;
            case "coupe":
                seatTypeId = SEAT_TYPE_ID_COUPE.ordinal();
                break;
            case "sedentary":
                seatTypeId = SEAT_TYPE_ID_SEDENTARY.ordinal();
                break;
            case "lux":
                seatTypeId = SEAT_TYPE_ID_LUX.ordinal();
                break;
            case "soft":
                seatTypeId = SEAT_TYPE_ID_SOFT.ordinal();
                break;
            default:
                seatTypeId = SEAT_TYPE_ID_COMMON.ordinal();
        }

        return seatTypeId;
    }

}

