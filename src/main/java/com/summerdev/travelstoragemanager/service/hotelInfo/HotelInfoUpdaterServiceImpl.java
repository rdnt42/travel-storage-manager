package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.decode.HotelInfoDecodeService;
import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.tutu.TutuStation;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import com.summerdev.travelstoragemanager.service.api.hotellook.HotelLookApiService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class HotelInfoUpdaterServiceImpl implements HotelInfoUpdaterService {

    @NonNull HotelLookApiService hotelLookApiService;
    @NonNull HotelInfoDecodeService hotelInfoDecodeService;
    @NonNull TutuStationRepository tutuStationRepository;
    @NonNull HotelInfoService hotelInfoService;


    @Override
    public int updateTravelInfo(Long cursorId) {
        // TODO rework search to return distinct cities
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
        List<HotelInfo> hotelInfos = hotelInfoDecodeService.decodeHotelsResponse(responses, totalDaysCount, city);

        return hotelInfoService.updateOrCreate(hotelInfos);
    }
}
