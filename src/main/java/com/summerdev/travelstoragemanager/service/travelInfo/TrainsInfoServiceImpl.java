package com.summerdev.travelstoragemanager.service.travelInfo;

import com.summerdev.travelstoragemanager.entity.TrainInfo;
import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.service.CursorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:28
 */
@Service
public class TrainsInfoServiceImpl implements TrainsInfoService, CursorService {

    @Override
    public Long getFirstCursorId() {
        return null;
    }

    @Override
    public Long getNextCursorId(long currCursorId) {
        return null;
    }

    @Override
    public int updateOrCreate(List<TrainInfo> trainInfos) {
        return 0;
    }


//
//    Map<Long, Optional<TrainInfo>> minCostTrains = trainInfos.stream()
//            .collect(Collectors.groupingBy(TrainInfo::getSeatTypeId,
//                    Collectors.minBy(Comparator.comparing(TrainInfo::getCost))));
//
//    List<TrainInfo> filteredList = minCostTrains.values().stream()
//            .flatMap(Optional::stream)
//            .collect(Collectors.toList());
//    private List<TrainInfo> createTrainsInfo(TutuRoute route) {
//    }
//
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
