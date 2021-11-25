package com.summerdev.travelstoragemanager.service.travelInfo;

import com.summerdev.travelstoragemanager.decode.HotelInfoDecodeService;
import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.HotelInfo;
import com.summerdev.travelstoragemanager.entity.tutu.TutuStation;
import com.summerdev.travelstoragemanager.repository.HotelInfoRepository;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
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
 * Date: 24.11.2021
 * Time: 0:09
 */
@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelsInfoServiceImpl implements TravelInfoService<HotelInfo>, CursorService {

    @NonNull HotelInfoRepository hotelInfoRepository;
    @NonNull HotelLookApiService hotelLookApiService;
    @NonNull HotelInfoDecodeService hotelInfoDecodeService;
    @NonNull TutuStationRepository tutuStationRepository;

    @Override
    public List<HotelInfo> getAllActualInfo() {
        return null;
    }

    @Override
    public void updateTravelInfo() {
        List<TutuStation> tutuStations = tutuStationRepository.findAll();
        for (int i = 0; i < 1; i++) {
            updateInfoForCity(tutuStations.get(i).getGeoName());
        }
//        for (TutuStation station : tutuStations) {
//            updateInfoForCity(station.getGeoName());
//        }
    }

    @Override
    public Long getFirstCursorId() {
        HotelInfo info = hotelInfoRepository.findFirstByOrderByIdAsc();

        return info == null ? null : info.getId();
    }

    public void updateInfoForCity(GeoNameData city) {
        int totalDaysCount = 30;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, totalDaysCount);
        Date endDate = calendar.getTime();

        hotelLookApiService
                .getHotelsInfo(city, startDate, endDate)
                .subscribe(responses -> {
                    List<HotelInfo> hotelInfos = hotelInfoDecodeService.decodeHotelsResponse(responses, totalDaysCount, city);
                    hotelInfoRepository.saveAll(hotelInfos);
                });
    }

    @Override
    public Long getLastCursorId() {
        return null;
    }
}
