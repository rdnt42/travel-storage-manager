package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.entity.tutu.TrainInfo;
import com.summerdev.travelstoragemanager.repository.TrainInfoRepository;
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
public class TrainInfoServiceImpl implements TrainInfoService {

    @NonNull TrainInfoRepository trainInfoRepository;

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
        TrainInfo itemToUpdate = trainInfoRepository.findByDepartureCityIdAndArrivalCityIdAndTrainNumber(
                        newInfo.getDepartureCityId(), newInfo.getDepartureCityId(), newInfo.getTrainNumber());

        if (itemToUpdate == null) {
            itemToUpdate = newInfo;
        } else {
            itemToUpdate.setTravelTime(newInfo.getTravelTime());
            itemToUpdate.addNewPrices(newInfo.getTrainPrices());
        }
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
