package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.tutu.TutuRoute;
import com.summerdev.travelstoragemanager.entity.tutu.TutuStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutuRouteRepository extends JpaRepository<TutuRoute, Long> {
    List<TutuRoute> findByDepartureStation(TutuStation departureStation);
}
