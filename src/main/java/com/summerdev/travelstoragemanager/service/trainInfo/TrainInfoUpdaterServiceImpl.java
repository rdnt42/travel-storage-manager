package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.adapter.TrainInfoAdapterService;
import com.summerdev.travelstoragemanager.aspect.LogUpdateCount;
import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import com.summerdev.travelstoragemanager.entity.train.TutuRoute;
import com.summerdev.travelstoragemanager.repository.TutuRouteRepository;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travelstoragemanager.service.TravelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.api.tutu.TutuApiService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 02.12.2021
 * Time: 23:43
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TrainInfoUpdaterServiceImpl implements TravelInfoUpdaterService {

    @NonNull TutuRouteRepository tutuRouteRepository;
    @NonNull TutuApiService tutuApiService;
    @NonNull TrainInfoAdapterService trainInfoAdapterService;
    @NonNull TrainInfoService trainsInfoService;

    @Override
    @LogUpdateCount
    public int updateTravelInfo(Long cursorId) {
        TutuRoute tutuRoute = tutuRouteRepository.findById(cursorId)
                .orElseThrow(() -> new NullPointerException("Route with id: " + cursorId +
                " not found"));

        TutuTrainsResponse trainsResponse = tutuApiService
                .getTrainsResponse(tutuRoute.getDepartureStation(), tutuRoute.getArrivalStation());

        List<TrainInfo> trainInfos = trainInfoAdapterService.convertResponsesToTrainsInfo(trainsResponse);

        return trainsInfoService.updateOrCreate(trainInfos);
    }
}
