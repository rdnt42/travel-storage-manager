package com.summerdev.travelstoragemanager.service.api.tutu;

import com.summerdev.travelstoragemanager.constant.api.Urls;
import com.summerdev.travelstoragemanager.entity.tutu.TutuStation;
import com.summerdev.travelstoragemanager.request.api.tutu.TutuRequest;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class TutuApiServiceImpl implements TutuApiService {
    @NonNull WebClient webClient;

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
            e.printStackTrace();
        }

        return new TutuTrainsResponse();
    }

    @Override
    public TutuTrainsResponse getTrainsResponse(TutuStation departureStation, TutuStation arrivalStation) {
        return getTrainsResponse(new TutuRequest(departureStation.getId().intValue(), arrivalStation.getId().intValue()));
    }



//    @Override
//    public List<TutuTrainsResponse> getTrainsInfo(GeoName departureCity) {
//        List<TutuTrainsResponse> responses = new ArrayList<>();
//
//        List<TutuStation> stations = tutuStationRepository.findByGeoName(departureCity);
//
//        for (TutuStation station : stations) {
//            List<TutuRoute> routes = tutuRouteRepository.findByDepartureStation(station);
//
//            for (TutuRoute route : routes) {
//                int departId = route.getDepartureStation().getStationId().intValue();
//                int arrivalId = route.getArrivalStation().getStationId().intValue();
//                try {
//                    TutuTrainsResponse response = getTrainsResponse(departId, arrivalId);
//                    if (response != null) {
//                        responses.add(response);
//                    }
//                } catch (Exception e) {
//                    log.error("Get train info failed", e);
//                }
//            }
//        }
//
//        return responses;
//    }
//
//    @Override
//    public List<TutuTrainsResponse> getTrainsInfo(String departureCityName) {
//        if (departureCityName == null || departureCityName.isEmpty()) {
//            throw new NullPointerException("DepartureCityName cannot be empty or null");
//        }
//        GeoName departureCity = geoNameRepository.findDistinctFirstByGeoNameRu(departureCityName)
//                .orElseThrow(() -> new NullPointerException("Departure city don't find"));
//
//        return getTrainsInfo(departureCity);
//    }
//
//    @Override
//    public TutuTrainsResponse getTrainsResponse(TutuStation departureStation, TutuStation arrivalStation) {
//        return getTrainsResponse(departureStation.getStationId().intValue(), arrivalStation.getStationId().intValue());
//    }
//
//    private TutuTrainsResponse getTrainsResponse(int departureStation, int arrivalStation) {
//        URI uri = UriComponentsBuilder.fromHttpUrl(Urls.URL_TUTU_GET_TRAINS)
//                .queryParam("term", departureStation)
//                .queryParam("term2", arrivalStation)
//                .build()
//                .toUri();
//        HttpResponse<String> response = httpRequestService.getResponseFromUri(uri);
//
//        if (response.statusCode() == HttpStatus.OK.value() && !response.body().equals("[]")) {
//            try {
//                return objectMapper.readValue(response.body(), TutuTrainsResponse.class);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return null;
//    }
}
