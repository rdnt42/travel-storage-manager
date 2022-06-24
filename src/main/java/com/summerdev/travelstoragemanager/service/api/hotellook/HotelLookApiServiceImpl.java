package com.summerdev.travelstoragemanager.service.api.hotellook;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.request.api.hotellook.HotelLookRequest;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import com.summerdev.travelstoragemanager.service.hotelInfo.HotelApiErrorHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 25.11.2021
 * Time: 22:32
 */
@RequiredArgsConstructor
@Service
public class HotelLookApiServiceImpl implements HotelLookApiService {
    private final WebClient webClient;
    private final HotelApiErrorHandlerService hotelTaskErrorHandlerService;

    @Value("${api.url.hotels.hotelLook}")
    private String hotelValue;

    @Override
    public List<HotelLookHotelResponse> getHotelsResponse(GeoNameData city, Date startDate, Date endDate) {
        checkRequestedParams(city, startDate, endDate);

        return getHotelsResponse(new HotelLookRequest(city.getGeoNameRu(), startDate, endDate));
    }

    private void checkRequestedParams(GeoNameData city, Date startDate, Date endDate) {
        if (city == null || city.getGeoNameRu() == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Parameters for HotelLookRequest cannot be null");
        }
    }
    private List<HotelLookHotelResponse> getHotelsResponse(HotelLookRequest request) {
        try {
            return getHotelsResponseFromApi(request);
        } catch (WebClientResponseException e) {
            hotelTaskErrorHandlerService.handleError(e, request);
            return new ArrayList<>();
        }
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    private List<HotelLookHotelResponse> getHotelsResponseFromApi(HotelLookRequest request) {
        String encodeLocation = URLEncoder.encode(request.getLocation(), StandardCharsets.UTF_8.name());
        URI uri = UriComponentsBuilder
                .fromHttpUrl(hotelValue)
                .queryParam("location", encodeLocation)
                .queryParam("checkIn", request.getCheckInFormatted())
                .queryParam("checkOut", request.getCheckOutFormatted())
                .queryParam("adults", request.getAdults())
                .queryParam("limit", request.getLimit())
                .queryParam("currency", request.getCurrency())
                .build(true)
                .toUri();

        return webClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<HotelLookHotelResponse>>() {})
                .block();
    }
}
