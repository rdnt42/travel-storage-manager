package com.summerdev.travelstoragemanager.adapter;

import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.summerdev.travelstoragemanager.entity.directory.ComfortType.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 22.05.2022
 * Time: 23:40
 */
class HotelInfoAdapterServiceTest {

    private HotelInfoAdapterService hotelInfoAdapterService;

    @BeforeEach
    void setUp() {
        hotelInfoAdapterService = new HotelInfoAdapterService();
    }

    @Test
    void convertResponsesEmptyResponsesSuccess() {
        List<HotelInfo> results =
                hotelInfoAdapterService.convertResponsesToHotelsInfo(getEmptyResponses(), null, 1);

        assertTrue(results.isEmpty());
    }

    @Test
    void convertResponsesWithValidObjectsSuccess() {
        List<HotelLookHotelResponse> responses = getFillTenObjectsInResponses();

        List<HotelInfo> results =
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, null, 1);

        assertEquals(10, results.size());
    }

    @Test
    void convertResponsesWithInvalidObjectsWithoutError() {
        List<HotelLookHotelResponse> responses = getTenObjectsWithThreeInvalid();

        List<HotelInfo> results = hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, null,1);

        assertEquals(7, results.size());
    }

    @Test
    void convertResponseTotalDaysZeroSuccess() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);

        HotelInfo hotelInfo = hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 0);

        assertNotNull(hotelInfo);
    }

    @Test
    void convertResponseComfortCheapForLess3Stars() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setStars(1L);

        HotelInfo hotelInfo = hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_CHEAP, hotelInfo.getHotelPrices().get(0).getComfortType());

        response.setStars(2L);

        hotelInfo = hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_CHEAP, hotelInfo.getHotelPrices().get(0).getComfortType());
    }

    @Test
    void convertResponseComfortComfortFrom3To4Stars() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setStars(3L);

        HotelInfo hotelInfo = hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_COMFORT, hotelInfo.getHotelPrices().get(0).getComfortType());

        response.setStars(4L);

        hotelInfo = hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_COMFORT, hotelInfo.getHotelPrices().get(0).getComfortType());
    }

    @Test
    void convertResponseComfortLuxuryFor5Stars() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setStars(5L);

        HotelInfo hotelInfo = hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_LUXURY, hotelInfo.getHotelPrices().get(0).getComfortType());
    }

    @Test
    void convertResponseWithEmptyPriceFromFailed() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setPriceFrom(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 1));

        assertTrue(thrown.getMessage().contains("Full cost for hotel cannot be null"));
    }

    @Test
    void convertResponseInfoWithEmptyIdSuccess() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 1));

        assertTrue(thrown.getMessage().contains("Hotel id cannot be null"));
    }

    @Test
    void convertResponseWithEmptyStarsFailed() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setStars(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 1));

        assertTrue(thrown.getMessage().contains("Stars for hotel cannot be null"));
    }

    @Test
    void convertResponseWithEmptyNameFailed() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setHotelName(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoAdapterService.convertResponseToHotelInfo(response, null, 1));

        assertTrue(thrown.getMessage().contains("Name for hotel cannot be null"));
    }

    private List<HotelLookHotelResponse> getEmptyResponses() {
        return Collections.emptyList();
    }

    private List<HotelLookHotelResponse> getFillTenObjectsInResponses() {
        List<HotelLookHotelResponse> responses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            responses.add(getFillHotelObjectResponse((long)i));
        }

        return responses;
    }

    private HotelLookHotelResponse getFillHotelObjectResponse(Long id) {
        return HotelLookHotelResponse.builder()
                .hotelId(id)
                .hotelName("Hotel")
                .stars(1L)
                .priceFrom(1.0)
                .build();
    }

    private HotelLookHotelResponse getFillHotelObjectResponseWithStars(long stars) {
        return HotelLookHotelResponse.builder()
                .hotelId(1L)
                .hotelName("Hotel")
                .stars(stars)
                .priceFrom(1.0)
                .build();
    }

    private List<HotelLookHotelResponse> getTenObjectsWithThreeInvalid() {
        List<HotelLookHotelResponse> responses = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            responses.add(getFillHotelObjectResponseWithStars(i));
        }

        responses.get(0).setHotelId(null);
        responses.get(1).setStars(null);
        responses.get(2).setHotelName(null);

        return responses;
    }
}
