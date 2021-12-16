package com.summerdev.travelstoragemanager.service.api.aviasales;

import com.summerdev.travelstoragemanager.config.ApiAviasalesProps;
import com.summerdev.travelstoragemanager.response.api.aviasales.AviaSalesPriceMapResponse;
import com.summerdev.travelstoragemanager.response.api.aviasales.bkp.AviaSalesMainResponse;
import com.summerdev.travelstoragemanager.util.UtilDateFormat;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AviasalesApiServiceImpl implements AviasalesApiService {

    @NonNull WebClient webClient;

    @NonNull ApiAviasalesProps apiAviasalesProps;

    public static final String PERIOD_MONTH = ":month";

    public List<AviaSalesPriceMapResponse> getPriceMap(String iataOrigin, Date fromDate, Integer durationDaysMin, Integer durationDaysMax) {
        Map<String, String> map = new HashMap<>();
        map.put("origin_iata", iataOrigin);
        map.put("period", UtilDateFormat.SHORT_DATE(fromDate) + PERIOD_MONTH);
        map.put("direct", "true");
        map.put("one_way", "false");
        map.put("no_visa", "true");
        map.put("schengen", "true");
        map.put("need_visa", "true");
        map.put("locale", "ru");
        map.put("min_trip_duration_in_days", durationDaysMin.toString());
        map.put("max_trip_duration_in_days", durationDaysMax.toString());
        try {
            AviaSalesPriceMapResponse[] response =
                    webClient
                    .get()
                    .uri(buildURI(apiAviasalesProps.getUrls().getPriceMap(), map))
                    .retrieve()
                    .bodyToMono(AviaSalesPriceMapResponse[].class)
                    .block();
            return response != null ? Arrays.asList(response) : new ArrayList<>(0);
        } catch (WebClientResponseException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(0);
    }

    private URI buildURI(String url, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        params.keySet()
                .forEach(key -> builder.queryParam(key, params.get(key)));
        builder.queryParam("token", apiAviasalesProps.getToken());
        return builder.build(true).toUri();
    }
}
