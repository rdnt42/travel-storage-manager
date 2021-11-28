package com.summerdev.travelstoragemanager.service.travelInfo;

import com.summerdev.travelstoragemanager.entity.TrainInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:28
 */
@Service
public class TrainsInfoServiceImpl implements TravelInfoService<TrainInfo>, CursorService {

    @Override
    public Long getFirstCursorId() {
        return null;
    }

    @Override
    public Long getNextCursorId(long currCursorId) {
        return null;
    }

    @Override
    public List<TrainInfo> getAllActualInfo() {
        return null;
    }

    @Override
    public void updateTravelInfo() {

    }


//    @Override
//    public void updateTravelInfo() {
//        long updatedCount = 0;
//
//        List<TutuRoute> routes = tutuRouteRepository.findAll();
//        for (TutuRoute route : routes) {
//            try {
//                List<TrainInfo> created = createTrainsInfo(route);
//                updatedCount += created.size();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        logger.info("Trains info data updated. Updated count: {}", updatedCount);
//    }
//
//    private List<TrainInfo> createTrainsInfo(TutuRoute route) {
//        TutuTrainsResponse response = tutuService.getTrainsResponse(route.getDepartureStation(), route.getArrivalStation());
//
//        if (response == null) return new ArrayList<>();
//
//        List<TrainInfo> trainInfos = new ArrayList<>();
//        for (TutuTripItemResponse trip : response.getTrips()) {
//            for (TutuRailwayCarriageResponse category : trip.getCategories()) {
//
//                TrainInfo trainIfo = getTrainInfo(trip, category);
//                if (trainIfo != null) {
//                    trainInfos.add(trainIfo);
//                }
//            }
//        }
//
//        if (trainInfos.isEmpty()) return new ArrayList<>();
//
//        Map<Long, Optional<TrainInfo>> minCostTrains = trainInfos.stream()
//                .collect(Collectors.groupingBy(TrainInfo::getSeatTypeId,
//                        Collectors.minBy(Comparator.comparing(TrainInfo::getCost))));
//
//        List<TrainInfo> filteredList = minCostTrains.values().stream()
//                .flatMap(Optional::stream)
//                .collect(Collectors.toList());
//
//        trainInfoRepository.saveAll(filteredList);
//
//        return filteredList;
//    }
//
//    private TrainInfo getTrainInfo(TutuTripItemResponse trip, TutuRailwayCarriageResponse category) {
//        TrainInfo trainIfo = new TrainInfo();
//        trainIfo.setTravelTime(trip.getTravelTimeInSeconds());
//
//        TutuStation arrivalStation = tutuStationRepository.findById(trip.getArrivalStation())
//                .orElse(null);
//        TutuStation departureStation = tutuStationRepository.findById(trip.getDepartureStation())
//                .orElse(null);
//
//        if (arrivalStation == null || departureStation == null) return null;
//
//        trainIfo.setDepartureCity(departureStation.getGeoName());
//        trainIfo.setArrivalCity(arrivalStation.getGeoName());
//
//        trainIfo.setCost((long) category.getPrice());
//        trainIfo.setSeatTypeId(getSeatTypeId(category.getType()));
//
//        return trainIfo;
//    }
//
//    private Long getSeatTypeId(String category) {
//        Long seatTypeId;
//        switch (category) {
//            case "plazcard":
//                seatTypeId = SEAT_TYPE_ID_ECONOMY;
//                break;
//            case "coupe":
//                seatTypeId = SEAT_TYPE_ID_COUPE;
//                break;
//            case "sedentary":
//                seatTypeId = SEAT_TYPE_ID_SEDENTARY;
//                break;
//            case "lux":
//                seatTypeId = SEAT_TYPE_ID_LUX;
//                break;
//            case "soft":
//                seatTypeId = SEAT_TYPE_ID_SOFT;
//                break;
//
//            default:
//                seatTypeId = SEAT_TYPE_ID_COMMON;
//        }
//
//        return seatTypeId;
//    }
}
