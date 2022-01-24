package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.entity.train.TutuRoute;
import com.summerdev.travelstoragemanager.repository.TutuRouteRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TrainCursorServiceImpl implements CursorService {
    @NonNull TutuRouteRepository tutuRouteRepository;

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
