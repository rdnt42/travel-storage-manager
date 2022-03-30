package com.summerdev.travelstoragemanager.adapter;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.hotel.HotelPrice;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 25.11.2021
 * Time: 23:08
 */

@Service
public class HotelInfoAdapterService {

    public List<HotelInfo> getHotelsInfo(List<HotelLookHotelResponse> responses, int totalDaysCount, GeoNameData city) {
        List<HotelInfo> hotelInfos = new ArrayList<>();

        for (HotelLookHotelResponse response : responses) {
            HotelInfo hotelInfo = convertResponseToHotelInfo(response, city);
            HotelPrice hotelPrice = getHotelPrice(hotelInfo, getCost(totalDaysCount, response.getPriceFrom()));

            if (hotelPrice != null) {
                hotelInfo.addPrice(hotelPrice);
            }
            hotelInfos.add(hotelInfo);
        }

        return hotelInfos;
    }

    private double getCost(int totalDaysCount, Double fullCost) {
        if (totalDaysCount == 0) {
            return 0.0;
        }

        return fullCost / totalDaysCount;
    }

    private HotelInfo convertResponseToHotelInfo(HotelLookHotelResponse hotel, GeoNameData city) {
        return HotelInfo.builder()
                .city(city)
                .stars(hotel.getStars())
                .hotelName(hotel.getHotelName())
                .id(hotel.getHotelId())
                .build();
    }

    private HotelPrice getHotelPrice(HotelInfo hotelInfo, double cost) {
        int stars = hotelInfo.getStars().intValue();
        if (stars < 3) {
            return getLowPrice(hotelInfo, cost, ComfortType.COMFORT_TYPE_CHEAP);
        } else if (stars == 3 || stars == 4) {
            return getLowPrice(hotelInfo, cost, ComfortType.COMFORT_TYPE_COMFORT);
        } else if (stars == 5) {
            return getLowPrice(hotelInfo, cost, ComfortType.COMFORT_TYPE_LUXURY);
        }

        return null;
    }

    private HotelPrice getLowPrice(HotelInfo hotelInfo, double cost, ComfortType comfortType) {
        return new HotelPrice(hotelInfo, cost, comfortType);
    }
}
