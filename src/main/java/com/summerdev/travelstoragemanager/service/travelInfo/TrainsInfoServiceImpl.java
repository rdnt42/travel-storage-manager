package com.summerdev.travelstoragemanager.service.travelInfo;

import com.summerdev.travelstoragemanager.entity.tutu.TrainInfo;
import com.summerdev.travelstoragemanager.repository.TrainInfoRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Transactional
    @Override
    public int updateOrCreate(List<TrainInfo> trainInfos) {
        List<TrainInfo> listForSave = new ArrayList<>();
        for (TrainInfo newItem : trainInfos) {
            listForSave.add(getItemForUpdate(newItem));
        }

        return trainInfoRepository.saveAll(listForSave).size();
    }

    private TrainInfo getItemForUpdate(TrainInfo newInfo) {
        TrainInfo itemToUpdate = trainInfoRepository.findByDepartureCityAndArrivalCityAndTrainNumber(
                        newInfo.getDepartureCity(), newInfo.getArrivalCity(), newInfo.getTrainNumber())
                .orElse(newInfo);

        itemToUpdate.setTravelTime(newInfo.getTravelTime());
        itemToUpdate.addNewPrices(newInfo.getTrainPrices());
        itemToUpdate.setLastUpdate(new Date());

        return itemToUpdate;
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
}
