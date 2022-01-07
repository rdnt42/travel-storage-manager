package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.tutu.TrainInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:53
 */
public interface TrainInfoRepository extends JpaRepository<TrainInfo, Long> {
    TrainInfo findFirstByOrderByIdAsc();

    TrainInfo findByDepartureCityAndArrivalCityAndTrainNumber(
            GeoNameData departureCity, GeoNameData arrivalCity, String trainNUmber);
}
