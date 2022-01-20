package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutuStationRepository extends JpaRepository<TutuStation, Long> {
    List<TutuStation> findByStationNameStartsWith(String name);

    List<TutuStation> findByGeoName(GeoNameData geoName);

    TutuStation findFirstByOrderByIdAsc();

    TutuStation findFirstByIdAfterOrderByIdAsc(Long id);

}
