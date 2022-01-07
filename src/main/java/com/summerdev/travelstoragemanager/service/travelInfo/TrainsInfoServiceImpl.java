package com.summerdev.travelstoragemanager.service.travelInfo;

import com.summerdev.travelstoragemanager.entity.tutu.TrainInfo;
import com.summerdev.travelstoragemanager.repository.TrainInfoRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:28
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TrainsInfoServiceImpl implements TrainsInfoService, CursorService {

    @NonNull TrainInfoRepository trainInfoRepository;

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
        List<TrainInfo> listForSave = new ArrayList<>();
        for (TrainInfo newItem : trainInfos) {
            TrainInfo updateItem = trainInfoRepository.findByDepartureCityAndArrivalCityAndTrainNumber(
                    newItem.getDepartureCity(), newItem.getArrivalCity(), newItem.getTrainNumber()
            );
            if (updateItem != null) {

            }

        }

        return 0;
    }

//    private void updateItem(TrainInfo info, TrainInfo newInfo) {
//        info.setTravelTime(newInfo.getTravelTime());
//        info.setArrivalCity(newInfo.getArrivalCity());
//        info.setDepartureCity(newInfo.getDepartureCity());
//        info.setSeatTypeId(newInfo.getId());
//        info.addNewPrices(newInfo.getHotelPrices());
//    }


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
}
