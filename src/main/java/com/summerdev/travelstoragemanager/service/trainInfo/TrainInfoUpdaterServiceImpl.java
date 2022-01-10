package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.decode.TrainInfoDecodeService;
import com.summerdev.travelstoragemanager.entity.tutu.TrainInfo;
import com.summerdev.travelstoragemanager.entity.tutu.TutuRoute;
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
    @NonNull TrainInfoDecodeService tutuInfoDecodeService;
    @NonNull TrainInfoService trainsInfoService;

    @Override
    public int updateTravelInfo(Long cursorId) {
        TutuRoute tutuRoute = tutuRouteRepository.findById(cursorId)
                .orElseThrow(() -> new NullPointerException("Route with id: " + cursorId +
                " not found"));

        TutuTrainsResponse trainsResponse = tutuApiService
                .getTrainsResponse(tutuRoute.getDepartureStation(), tutuRoute.getArrivalStation());

        List<TrainInfo> trainInfos = tutuInfoDecodeService.decodeTrainsResponse(trainsResponse);

        return trainsInfoService.updateOrCreate(trainInfos);
    }
}
