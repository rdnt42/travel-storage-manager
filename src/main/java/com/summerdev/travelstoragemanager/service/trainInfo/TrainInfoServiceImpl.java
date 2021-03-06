package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import com.summerdev.travelstoragemanager.repository.TrainInfoRepository;
import com.summerdev.travelstoragemanager.serviceType.TutuServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:28
 */
@RequiredArgsConstructor
@Service
public class TrainInfoServiceImpl implements TrainInfoService, TutuServiceType {
    private final TrainInfoRepository trainInfoRepository;

    @Transactional
    @Override
    public int updateOrCreate(List<TrainInfo> trainInfos) {
        trainInfos = getFilteredTrainInfos(trainInfos);

        List<TrainInfo> listForSave = new ArrayList<>();
        for (TrainInfo newItem : trainInfos) {
            listForSave.add(getItemForUpdate(newItem));
        }

        return trainInfoRepository.saveAll(listForSave).size();
    }

    private List<TrainInfo> getFilteredTrainInfos(List<TrainInfo> trainInfos) {
        return trainInfos
                .stream()
                .filter(distinctByKey(t -> Arrays.asList(
                        t.getDepartureCity().getId(), t.getArrivalCity().getId(), t.getTrainNumber())))
                .toList();
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();

        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private TrainInfo getItemForUpdate(TrainInfo newInfo) {
        TrainInfo itemToUpdate = trainInfoRepository.findDistinctByDepartureCityIdAndArrivalCityIdAndTrainNumber(
                newInfo.getDepartureCity().getId(), newInfo.getArrivalCity().getId(), newInfo.getTrainNumber());

        if (itemToUpdate == null) {
            itemToUpdate = newInfo;
        } else {
            itemToUpdate.setTravelTime(newInfo.getTravelTime());
            itemToUpdate.addNewPrices(newInfo.getTrainPrices());
        }
        itemToUpdate.setLastUpdate(new Date());

        return itemToUpdate;
    }
}
