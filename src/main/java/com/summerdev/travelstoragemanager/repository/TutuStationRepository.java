package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutuStationRepository extends JpaRepository<TutuStation, Long> {
    TutuStation findFirstByOrderByIdAsc();

    TutuStation findFirstByIdAfterOrderByIdAsc(Long id);

    TutuStation findFirstByIdAfterAndGeoNameIdNotOrderByIdAsc(Long id, Long geoNameId);
}
