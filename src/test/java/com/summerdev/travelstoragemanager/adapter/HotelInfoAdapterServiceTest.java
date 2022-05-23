package com.summerdev.travelstoragemanager.adapter;

import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.hotel.HotelPrice;
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
    public void init() {
        hotelInfoAdapterService = new HotelInfoAdapterService();
    }

    @Test
    void getHotelsInfoEmptyResponsesSuccessTest() {
        List<HotelInfo> results =
                hotelInfoAdapterService.convertResponsesToHotelsInfo(getEmptyResponses(), 10, null);

        assertTrue(results.isEmpty());
    }

    @Test
    void getHotelsInfoWithEmptyPriceFromFailed() {
        List<HotelLookHotelResponse> responses = getObjectWithEmptyPriceFromInResponses();

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, 1, null));

        assertTrue(thrown.getMessage().contains("Full cost for hotel cannot be null"));
    }

    @Test
    void getHotelsInfoWithEmptyIdFailed() {
        List<HotelLookHotelResponse> responses = getObjectWithEmptyIdInResponses();

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, 1, null));

        assertTrue(thrown.getMessage().contains("Hotel id cannot be null"));
    }

    @Test
    void getHotelsInfoWithEmptyStarsFailed() {
        List<HotelLookHotelResponse> responses = getObjectWithEmptyStarsInResponses();

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, 1, null));

        assertTrue(thrown.getMessage().contains("Stars for hotel cannot be null"));
    }

    @Test
    void getHotelsInfoWithEmptyNameFailed() {
        List<HotelLookHotelResponse> responses = getObjectWithEmptyNameInResponses();

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, 1, null));

        assertTrue(thrown.getMessage().contains("Name for hotel cannot be null"));
    }

    @Test
    void getHotelsInfoTotalDaysZeroSuccess() {
        int count = 1;
        List<HotelLookHotelResponse> responses = getFillObjectsInResponses(count);

        List<HotelInfo> hotelInfos =
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, 0, null);

        assertEquals(count, hotelInfos.size());
    }

    @Test
    void getHotelsInfoTenObjectsInResponsesSuccessTest() {
        int count = 10;
        List<HotelLookHotelResponse> responses = getFillObjectsInResponses(count);

        List<HotelInfo> results =
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, 1, null);

        assertEquals(count, results.size());
    }

    @Test
    void getHotelsComfortCheapForLess3Stars() {
        List<HotelLookHotelResponse> responses = getCheapHotelsList();

        List<HotelInfo> results =
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, 1, null);

        for (HotelInfo result : results) {
            HotelPrice hotelPrice = result.getHotelPrices().get(0);
            assertEquals(COMFORT_TYPE_CHEAP, hotelPrice.getComfortType());
        }
    }

    @Test
    void getHotelsComfortComfortFrom3To4Stars() {
        List<HotelLookHotelResponse> responses = getComfortHotelsList();

        List<HotelInfo> results =
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, 1, null);

        for (HotelInfo result : results) {
            HotelPrice hotelPrice = result.getHotelPrices().get(0);
            assertEquals(COMFORT_TYPE_COMFORT, hotelPrice.getComfortType());
        }
    }

    @Test
    void getHotelsComfortLuxuryFor5Stars() {
        List<HotelLookHotelResponse> responses = getLuxuryHotelsList();

        List<HotelInfo> results =
                hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, 1, null);

        for (HotelInfo result : results) {
            HotelPrice hotelPrice = result.getHotelPrices().get(0);
            assertEquals(COMFORT_TYPE_LUXURY, hotelPrice.getComfortType());
        }
    }

    private List<HotelLookHotelResponse> getEmptyResponses() {
        return Collections.emptyList();
    }

    private List<HotelLookHotelResponse> getObjectWithEmptyPriceFromInResponses() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setPriceFrom(null);

        return Collections.singletonList(response);
    }

    private List<HotelLookHotelResponse> getObjectWithEmptyIdInResponses() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(null);

        return Collections.singletonList(response);
    }

    private List<HotelLookHotelResponse> getObjectWithEmptyStarsInResponses() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setStars(null);

        return Collections.singletonList(response);
    }

    private List<HotelLookHotelResponse> getObjectWithEmptyNameInResponses() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setHotelName(null);

        return Collections.singletonList(response);
    }

    private List<HotelLookHotelResponse> getFillObjectsInResponses(int count) {
        List<HotelLookHotelResponse> responses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
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

    private List<HotelLookHotelResponse> getCheapHotelsList() {
        List<HotelLookHotelResponse> responses = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            responses.add(getFillHotelObjectResponseWithStars(i));
        }

        return responses;
    }

    private List<HotelLookHotelResponse> getComfortHotelsList() {
        List<HotelLookHotelResponse> responses = new ArrayList<>();

        for (int i = 3; i <= 4; i++) {
            responses.add(getFillHotelObjectResponseWithStars(i));
        }

        return responses;
    }

    private List<HotelLookHotelResponse> getLuxuryHotelsList() {
        HotelLookHotelResponse response = getFillHotelObjectResponseWithStars(5);

        return Collections.singletonList(response);
    }

    private HotelLookHotelResponse getFillHotelObjectResponseWithStars(long stars) {
        return HotelLookHotelResponse.builder()
                .hotelId(1L)
                .hotelName("Hotel")
                .stars(stars)
                .priceFrom(1.0)
                .build();
    }
}
