package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.11.2021
 * Time: 21:49
 */
public interface HotelInfoService {
    int updateOrCreate(List<HotelInfo> hotelInfos);
}
