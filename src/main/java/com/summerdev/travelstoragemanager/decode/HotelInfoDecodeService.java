package com.summerdev.travelstoragemanager.decode;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.HotelInfo;
import com.summerdev.travelstoragemanager.repository.GeoNameDataRepository;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @NonNull GeoNameDataRepository geoNameDataRepository;

    public List<HotelInfo> decodeHotelsResponse(List<HotelLookHotelResponse> responses, int totalDaysCount, GeoNameData city) {
        List<HotelInfo> hotelInfos = new ArrayList<>();

        for (HotelLookHotelResponse hotel : responses) {
            hotelInfos.add(getLowPriceHotel(hotel, city, totalDaysCount));
            hotelInfos.add(getMediumPriceHotel(hotel, city, totalDaysCount));
            hotelInfos.add(getHighPriceHotel(hotel, city, totalDaysCount));
        }

        return hotelInfos;
    }

    private Long getCost(int totalDaysCount, Double fullCost) {
        return (long) (fullCost / totalDaysCount);
    }

    private GeoNameData getCity(String city) {
        return geoNameDataRepository.findFirstByGeoNameRu(city).orElse(null);
    }

    private HotelInfo getLowPriceHotel(HotelLookHotelResponse hotel, GeoNameData city, int totalDaysCount) {
        HotelInfo hotelInfo = new HotelInfo();

        hotelInfo.setCity(city);
        hotelInfo.setStars(hotel.getStars());
        hotelInfo.setCost(getCost(totalDaysCount, hotel.getPricePercentile().getSmallPrice()));

        return hotelInfo;
    }

    private HotelInfo getMediumPriceHotel(HotelLookHotelResponse hotel, GeoNameData city, int totalDaysCount) {
        HotelInfo hotelInfo = new HotelInfo();

        hotelInfo.setCity(city);
        hotelInfo.setStars(hotel.getStars());
        hotelInfo.setCost(getCost(totalDaysCount, hotel.getPricePercentile().getMediumPrice()));

        return hotelInfo;
    }

    private HotelInfo getHighPriceHotel(HotelLookHotelResponse hotel, GeoNameData city, int totalDaysCount) {
        HotelInfo hotelInfo = new HotelInfo();

        hotelInfo.setCity(city);
        hotelInfo.setStars(hotel.getStars());
        hotelInfo.setCost(getCost(totalDaysCount, hotel.getPricePercentile().getHighPrice()));

        return hotelInfo;
    }
}
