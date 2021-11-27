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
            hotelInfo.addPrice(getLowPrice(hotelInfo.getId(), response, totalDaysCount));
            hotelInfo.addPrice(getMediumPrice(hotelInfo.getId(), response, totalDaysCount));
            hotelInfo.addPrice(getHighPrice(hotelInfo.getId(), response, totalDaysCount));
            hotelInfo.setLastUpdate(new Date());

            hotelInfos.add(hotelInfo);
        }

        return hotelInfos;
    }

    private Long getCost(int totalDaysCount, Double fullCost) {
        return (long) (fullCost / totalDaysCount);
    }

    private HotelPrice getLowPrice(Long hotelId, HotelLookHotelResponse hotel, int totalDaysCount) {
        return new HotelPrice(hotelId, getCost(totalDaysCount, hotel.getPriceFrom()),
                ComfortType.COMFORT_TYPE_CHEAP);
    }

    private HotelPrice getMediumPrice(Long hotelId, HotelLookHotelResponse hotel, int totalDaysCount) {
        return new HotelPrice(hotelId, getCost(totalDaysCount, hotel.getPriceAvg()),
                ComfortType.COMFORT_TYPE_COMFORT);
    }

    private HotelPrice getHighPrice(Long hotelId, HotelLookHotelResponse hotel, int totalDaysCount) {
        return new HotelPrice(hotelId, getCost(totalDaysCount, hotel.getPricePercentile().getHighPrice()),
                ComfortType.COMFORT_TYPE_LUXURY);
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
