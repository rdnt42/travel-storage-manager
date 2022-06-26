package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.adapter.HotelInfoAdapterService;
import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import com.summerdev.travelstoragemanager.service.TravelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.api.hotellook.HotelLookApiService;
import com.summerdev.travelstoragemanager.serviceType.HotelLookServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.11.2021
 * Time: 21:47
 */
@RequiredArgsConstructor
@Service
public class HotelInfoUpdaterServiceImpl implements TravelInfoUpdaterService, HotelLookServiceType {
    private final HotelLookApiService hotelLookApiService;
    private final HotelInfoAdapterService hotelInfoAdapterService;
    private final TutuStationRepository tutuStationRepository;
    private final HotelInfoService hotelInfoService;

    @Override
    public int updateTravelInfo(Long cursorId) {
        TutuStation station = tutuStationRepository.findById(cursorId)
                .orElseThrow(() -> new NullPointerException("Station with id: " + cursorId +
                        " not found"));

        return updateInfoForCity(station.getGeoName());
    }

    private int updateInfoForCity(GeoNameData city) {
        int totalDaysCount = 30;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, totalDaysCount);
        Date endDate = calendar.getTime();

        List<HotelLookHotelResponse> responses = hotelLookApiService.getHotelsResponse(city, startDate, endDate);
        List<HotelInfo> hotelInfos = hotelInfoAdapterService.convertResponsesToHotelsInfo(responses, city, totalDaysCount);

        return hotelInfoService.updateOrCreate(hotelInfos);
    }
}
