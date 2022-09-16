package com.summerdev.travelstoragemanager.adapter;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.hotel.HotelPrice;
import com.summerdev.travelstoragemanager.enums.ComfortTypes;
import com.summerdev.travelstoragemanager.repository.ComfortTypeRepository;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 25.11.2021
 * Time: 23:08
 */

@Slf4j
@AllArgsConstructor
@Service
public class HotelInfoAdapterService {
    private final ComfortTypeRepository comfortTypeRepository;

    public List<HotelInfo> convertResponsesToHotelsInfo(List<HotelLookHotelResponse> responses, GeoNameData city, int totalDaysCount) {
        return responses.stream()
                .map(r -> tryConvertResponseToHotelInfo(r, city, totalDaysCount))
                .filter(Objects::nonNull)
                .toList();
    }

    private HotelInfo tryConvertResponseToHotelInfo(HotelLookHotelResponse hotel, GeoNameData city, int totalDaysCount) {
        try {
            return convertResponseToHotelInfo(hotel, city, totalDaysCount);
        } catch (Exception e) {
            log.warn("Some unexpected exception when parse Hotel response, message: {}", e.getMessage());
        }

        return null;
    }

    public HotelInfo convertResponseToHotelInfo(HotelLookHotelResponse hotel, GeoNameData city, int totalDaysCount) {
        hotelInfoValidate(hotel);

        HotelInfo hotelInfo = getHotelInfoFromBuilder(hotel, city);
        HotelPrice hotelPrice = getHotelPrice(hotelInfo, getCost(totalDaysCount, hotel.getPriceFrom()));

        hotelInfo.addPrice(hotelPrice);

        return hotelInfo;
    }

    private HotelInfo getHotelInfoFromBuilder(HotelLookHotelResponse hotel, GeoNameData city) {
        return HotelInfo.builder()
                .city(city)
                .stars(hotel.getStars())
                .hotelName(hotel.getHotelName())
                .id(hotel.getHotelId())
                .build();
    }

    private double getCost(int totalDaysCount, Double fullCost) {
        if (fullCost == null) {
            throw new IllegalArgumentException("Full cost for hotel cannot be null");
        }
        if (totalDaysCount == 0) {
            return 0.0;
        }

        return fullCost / totalDaysCount;
    }

    private void hotelInfoValidate(HotelLookHotelResponse hotel) {
        if (hotel.getHotelId() == null) {
            throw new IllegalArgumentException("Hotel id cannot be null");
        }

        if (hotel.getStars() == null) {
            throw new IllegalArgumentException("Stars for hotel cannot be null");
        }

        if (hotel.getHotelName() == null) {
            throw new IllegalArgumentException("Name for hotel cannot be null");
        }
    }

    private HotelPrice getHotelPrice(HotelInfo hotelInfo, double cost) {
        int stars = hotelInfo.getStars().intValue();
        if (stars == 3 || stars == 4) {
            return getNewPrice(hotelInfo, cost, ComfortTypes.COMFORT_TYPE_COMFORT.getId());
        } else if (stars == 5) {
            return getNewPrice(hotelInfo, cost, ComfortTypes.COMFORT_TYPE_LUXURY.getId());
        } else {
            return getNewPrice(hotelInfo, cost, ComfortTypes.COMFORT_TYPE_CHEAP.getId());
        }
    }

    private HotelPrice getNewPrice(HotelInfo hotelInfo, double cost, Integer comfortTypeId) {
        ComfortType comfortType = comfortTypeRepository.findById(comfortTypeId)
                .orElseThrow(() -> new NullPointerException("Comfort type with id: " + comfortTypeId + "doesn't exist"));
        return new HotelPrice(hotelInfo, cost, comfortType);
    }
}
