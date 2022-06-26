package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.entity.train.TutuRoute;
import com.summerdev.travelstoragemanager.repository.TutuRouteRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.serviceType.TutuServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TrainCursorServiceImpl implements CursorService, TutuServiceType {
    private final TutuRouteRepository tutuRouteRepository;

    @Override
    public Long getFirstCursorId() {
        TutuRoute route = tutuRouteRepository.findFirstByOrderByIdAsc();

        return route == null ? null : route.getId();
    }

    @Override
    public Long getNextCursorId(long currCursorId) {
        TutuRoute route = tutuRouteRepository.findFirstByIdAfterOrderByIdAsc(currCursorId);

        return route == null ? null : route.getId();
    }

}
