package com.summerdev.travelstoragemanager.service.api.tutu;

import com.summerdev.travelstoragemanager.constant.api.Urls;
import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.request.api.tutu.TutuRequest;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travelstoragemanager.service.trainInfo.TrainApiErrorHandlerService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TutuApiServiceImpl implements TutuApiService {
    @NonNull WebClient webClient;
    @NonNull TrainApiErrorHandlerService trainApiErrorHandlerService;

    @Override
    public TutuTrainsResponse getTrainsResponse(TutuRequest request) {
        URI uri = UriComponentsBuilder.fromHttpUrl(Urls.URL_TUTU_GET_TRAINS)
                .queryParam("term", request.getDepartureStation())
                .queryParam("term2", request.getArrivalStation())
                .build(true)
                .toUri();
        try {
            return webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(TutuTrainsResponse.class)
                    .block();
        } catch (WebClientResponseException e) {
            trainApiErrorHandlerService.handleError(e, request);
        }

        return new TutuTrainsResponse();
    }

    @Override
    public TutuTrainsResponse getTrainsResponse(TutuStation departureStation, TutuStation arrivalStation) {
        return getTrainsResponse(new TutuRequest(departureStation.getId().intValue(), arrivalStation.getId().intValue()));
    }
}
