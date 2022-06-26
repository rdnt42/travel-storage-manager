package com.summerdev.travelstoragemanager.service.api.tutu;

import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.request.api.tutu.TutuRequest;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travelstoragemanager.service.trainInfo.TrainApiErrorHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class TutuApiServiceImpl implements TutuApiService {
    private final WebClient webClient;
    private final TrainApiErrorHandlerService trainApiErrorHandlerService;

    @Value("${api.url.trains.tutu}")
    private String tutuUrl;

    @Override
    public TutuTrainsResponse getTrainsResponse(TutuStation departureStation, TutuStation arrivalStation) {
        checkRequestedParams(departureStation, arrivalStation);
        return getTrainsResponse(new TutuRequest(departureStation.getId().intValue(), arrivalStation.getId().intValue()));
    }

    private void checkRequestedParams(TutuStation departureStation, TutuStation arrivalStation) {
        if (departureStation == null) {
            throw new IllegalArgumentException("DepartureStation for request cannot be null");
        }

        if (arrivalStation == null) {
            throw new IllegalArgumentException("ArrivalStation for request cannot be null");
        }
    }

    private TutuTrainsResponse getTrainsResponse(TutuRequest request) {
        try {
            return getTrainsResponseFromApi(request);
        } catch (WebClientResponseException e) {
            trainApiErrorHandlerService.handleError(e, request);
            return new TutuTrainsResponse();
        }
    }

    private TutuTrainsResponse getTrainsResponseFromApi(TutuRequest request) {
        URI uri = UriComponentsBuilder.fromHttpUrl(tutuUrl)
                .queryParam("service", "tutu_trains")
                .queryParam("term", request.getDepartureStation())
                .queryParam("term2", request.getArrivalStation())
                .build(true)
                .toUri();

        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(TutuTrainsResponse.class)
                .block();
    }
}
