package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.train.TutuRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutuRouteRepository extends JpaRepository<TutuRoute, Long> {
    TutuRoute findFirstByOrderByIdAsc();

    TutuRoute findFirstByIdAfterOrderByIdAsc(Long id);
}
