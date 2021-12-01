package com.summerdev.travelstoragemanager.service.api.hotellook;

import com.summerdev.travelstoragemanager.constant.api.Urls;
import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.error.HotelTaskErrorHandlerService;
import com.summerdev.travelstoragemanager.request.api.hotellook.HotelLookRequest;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class HotelLookApiServiceImpl implements HotelLookApiService {
    WebClient webClient;
    HotelTaskErrorHandlerService hotelTaskErrorHandlerService;

    @Override
    public List<HotelLookHotelResponse> getHotelsResponse(HotelLookRequest request) {
        String encodeLocation;

        try {
            encodeLocation = URLEncoder.encode(request.getLocation(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        URI uri = UriComponentsBuilder
                .fromHttpUrl(Urls.URL_HOTEL_LOOK_GET_HOTELS)
                .queryParam("location", encodeLocation)
                .queryParam("checkIn", request.getCheckInFormatted())
                .queryParam("checkOut", request.getCheckOutFormatted())
                .queryParam("adults", request.getAdults())
                .queryParam("limit", request.getLimit())
                .queryParam("currency", request.getCurrency())
                .build(true)
                .toUri();

        try {
            return webClient
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<HotelLookHotelResponse>>() {})
                    .block();
        } catch (WebClientResponseException e) {
            hotelTaskErrorHandlerService.errorHandler(e, request);
        }

        return new ArrayList<>();
    }

    @Override
    public List<HotelLookHotelResponse> getHotelsInfo(GeoNameData city, Date startDate, Date endDate) {
        return getHotelsResponse(new HotelLookRequest(city.getGeoNameRu(), startDate, endDate));
    }
}
