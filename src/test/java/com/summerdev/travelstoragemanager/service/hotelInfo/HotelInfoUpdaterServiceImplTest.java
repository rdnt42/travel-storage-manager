package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.adapter.HotelInfoAdapterService;
import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import com.summerdev.travelstoragemanager.service.api.hotellook.HotelLookApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 08.09.2022
 * Time: 21:08
 */
@ExtendWith(MockitoExtension.class)
class HotelInfoUpdaterServiceImplTest {
    @InjectMocks
    private HotelInfoUpdaterServiceImpl hotelInfoUpdaterService;

    @Mock
    private TutuStationRepository tutuStationRepository;
    @Mock
    private HotelLookApiService hotelLookApiService;
    @Mock
    private HotelInfoAdapterService hotelInfoAdapterService;
    @Mock
    private HotelInfoService hotelInfoService;

    @Test
    void updateTravelInfoCursorFailed() {
        long testId = 1;
        when(tutuStationRepository.findById(testId))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(NullPointerException.class, () ->
                hotelInfoUpdaterService.updateTravelInfo(testId));

        assertEquals("Station with id: " + testId + " not found", exception.getMessage());
    }

    @Test
    void updateTravelInfoSuccess() {
        TutuStation station = getTutuStation();
        GeoNameData geoNameData = station.getGeoName();
        List<HotelLookHotelResponse> responses = getHotelResponseList();
        List<HotelInfo> hotelInfos = getHotelInfos();

        when(tutuStationRepository.findById(anyLong()))
                .thenReturn(Optional.of(station));
        when(hotelLookApiService.getHotelsResponse(eq(geoNameData), any(Date.class), any(Date.class)))
                .thenReturn(responses);
        when(hotelInfoAdapterService.convertResponsesToHotelsInfo(eq(responses), eq(geoNameData), anyInt()))
                .thenReturn(hotelInfos);
        when(hotelInfoService.updateOrCreate(hotelInfos))
                .thenReturn(hotelInfos.size());

        int count = hotelInfoUpdaterService.updateTravelInfo(1L);

        assertEquals(hotelInfos.size(), count);
    }


    private TutuStation getTutuStation() {
        TutuStation station = new TutuStation();
        station.setGeoName(getGeoNameData());

        return station;
    }
    public GeoNameData getGeoNameData() {
        return new GeoNameData();
    }

    private List<HotelLookHotelResponse> getHotelResponseList() {
        return List.of(new HotelLookHotelResponse(), new HotelLookHotelResponse());
    }

    private List<HotelInfo> getHotelInfos() {
        return List.of(new HotelInfo() , new HotelInfo(), new HotelInfo());
    }
}