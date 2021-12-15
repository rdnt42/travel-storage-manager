package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.tutu.TutuRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutuRouteRepository extends JpaRepository<TutuRoute, Long> {
    TutuRoute findFirstByIdAfterOrderByIdAsc(Long id);
}
