package com.summerdev.travelstoragemanager.decode;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.hotel.HotelPrice;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 25.11.2021
 * Time: 23:08
 */
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class HotelInfoDecodeService {

    public List<HotelInfo> decodeHotelsResponse(List<HotelLookHotelResponse> responses, int totalDaysCount, GeoNameData city) {
        List<HotelInfo> hotelInfos = new ArrayList<>();

        for (HotelLookHotelResponse response : responses) {
            HotelInfo hotelInfo = getCommonHotel(response, city);
            int stars = response.getStars().intValue();
            if (stars < 3) {
                hotelInfo.addPrice(getLowPrice(hotelInfo, response, totalDaysCount, ComfortType.COMFORT_TYPE_CHEAP));
            } else if (stars == 3 || stars == 4) {
                hotelInfo.addPrice(getLowPrice(hotelInfo, response, totalDaysCount, ComfortType.COMFORT_TYPE_COMFORT));
            } else if (stars == 5) {
                hotelInfo.addPrice(getLowPrice(hotelInfo, response, totalDaysCount, ComfortType.COMFORT_TYPE_LUXURY));
            }

            hotelInfos.add(hotelInfo);
        }

        return hotelInfos;
    }

    private Double getCost(int totalDaysCount, Double fullCost) {
        if (totalDaysCount == 0) {
            return 0.0;
        }
        
        return fullCost / totalDaysCount;
    }

    private HotelPrice getLowPrice(HotelInfo hotelInfo, HotelLookHotelResponse hotel, int totalDaysCount,
                                   ComfortType comfortType) {
        return new HotelPrice(hotelInfo, getCost(totalDaysCount, hotel.getPriceFrom()), comfortType);
    }

    private HotelInfo getCommonHotel(HotelLookHotelResponse hotel, GeoNameData city) {
        HotelInfo hotelInfo = new HotelInfo();

        hotelInfo.setCity(city);
        hotelInfo.setStars(hotel.getStars());
        hotelInfo.setHotelName(hotel.getHotelName());
        hotelInfo.setId(hotel.getHotelId());

        return hotelInfo;
    }
}
