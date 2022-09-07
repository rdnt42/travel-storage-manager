package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:53
 */
public interface TrainInfoRepository extends JpaRepository<TrainInfo, Long> {
    @Query("select distinct t from TrainInfo t where t.departureCity.id = ?1 and t.arrivalCity.id = ?2 and t.trainNumber = ?3")
    Optional<TrainInfo> findDistinctTrain(Long departureCityId, Long arrivalCityId, String trainNUmber);
}
