package com.summerdev.travelstoragemanager.converter;

import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.repository.ComfortTypeRepository;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import com.summerdev.travelstoragemanager.service.converter.HotelInfoConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.summerdev.travelstoragemanager.enums.ComfortTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 22.05.2022
 * Time: 23:40
 */
@ExtendWith(MockitoExtension.class)
class HotelInfoConverterTest {
    @InjectMocks
    private HotelInfoConverter hotelInfoConverter;

    @Mock
    private ComfortTypeRepository comfortTypeRepository;

    @Test
    void convertResponsesEmptyResponsesSuccess() {
        List<HotelInfo> results =
                hotelInfoConverter.convertResponsesToHotelsInfo(getEmptyResponses(), null, 1);

        assertTrue(results.isEmpty());
    }

    @Test
    void convertResponsesWithValidObjectsSuccess() {
        when(comfortTypeRepository.findById(anyInt()))
                .thenReturn(Optional.of(new ComfortType()));

        List<HotelLookHotelResponse> responses = getFillTenObjectsInResponses();

        List<HotelInfo> results =
                hotelInfoConverter.convertResponsesToHotelsInfo(responses, null, 1);

        assertEquals(10, results.size());
    }

    @Test
    void convertResponsesWithInvalidObjectsWithoutError() {
        when(comfortTypeRepository.findById(anyInt()))
                .thenReturn(Optional.of(new ComfortType()));

        List<HotelLookHotelResponse> responses = getTenObjectsWithThreeInvalid();

        List<HotelInfo> results = hotelInfoConverter.convertResponsesToHotelsInfo(responses, null, 1);

        assertEquals(7, results.size());
    }

    @Test
    void convertResponseTotalDaysZeroSuccess() {
        when(comfortTypeRepository.findById(anyInt()))
                .thenReturn(Optional.of(new ComfortType()));
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);

        HotelInfo hotelInfo = hotelInfoConverter.convertResponseToHotelInfo(response, null, 0);

        assertNotNull(hotelInfo);
    }

    @Test
    void convertResponseComfortCheapForLess3Stars() {
        when(comfortTypeRepository.findById(1))
                .thenReturn(Optional.of(getComfortType(1)));

        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setStars(1L);

        HotelInfo hotelInfo = hotelInfoConverter.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_CHEAP.getId(), hotelInfo.getHotelPrices().get(0).getComfortType().getId());

        response.setStars(2L);

        hotelInfo = hotelInfoConverter.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_CHEAP.getId(), hotelInfo.getHotelPrices().get(0).getComfortType().getId());
    }

    @Test
    void convertResponseComfortComfortFrom3To4Stars() {
        when(comfortTypeRepository.findById(2))
                .thenReturn(Optional.of(getComfortType(2)));

        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setStars(3L);

        HotelInfo hotelInfo = hotelInfoConverter.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_COMFORT.getId(), hotelInfo.getHotelPrices().get(0).getComfortType().getId());

        response.setStars(4L);

        hotelInfo = hotelInfoConverter.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_COMFORT.getId(), hotelInfo.getHotelPrices().get(0).getComfortType().getId());
    }

    @Test
    void convertResponseComfortLuxuryFor5Stars() {
        when(comfortTypeRepository.findById(3))
                .thenReturn(Optional.of(getComfortType(3)));

        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setStars(5L);

        HotelInfo hotelInfo = hotelInfoConverter.convertResponseToHotelInfo(response, null, 0);
        assertEquals(COMFORT_TYPE_LUXURY.getId(), hotelInfo.getHotelPrices().get(0).getComfortType().getId());
    }

    @Test
    void convertResponseWithEmptyPriceFromFailed() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setPriceFrom(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoConverter.convertResponseToHotelInfo(response, null, 1));

        assertTrue(thrown.getMessage().contains("Full cost for hotel cannot be null"));
    }

    @Test
    void convertResponseInfoWithEmptyIdSuccess() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoConverter.convertResponseToHotelInfo(response, null, 1));

        assertTrue(thrown.getMessage().contains("Hotel id cannot be null"));
    }

    @Test
    void convertResponseWithEmptyStarsFailed() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setStars(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoConverter.convertResponseToHotelInfo(response, null, 1));

        assertTrue(thrown.getMessage().contains("Stars for hotel cannot be null"));
    }

    @Test
    void convertResponseWithEmptyNameFailed() {
        HotelLookHotelResponse response = getFillHotelObjectResponse(1L);
        response.setHotelName(null);

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoConverter.convertResponseToHotelInfo(response, null, 1));

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

    private ComfortType getComfortType(int id) {
        ComfortType comfortType = new ComfortType();
        comfortType.setId(id);

        return comfortType;
    }
}
