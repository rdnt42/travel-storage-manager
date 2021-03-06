package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:53
 */
public interface TrainInfoRepository extends JpaRepository<TrainInfo, Long> {
    TrainInfo findDistinctByDepartureCityIdAndArrivalCityIdAndTrainNumber(
            Long departureCityId, Long arrivalCityId, String trainNUmber);
}
