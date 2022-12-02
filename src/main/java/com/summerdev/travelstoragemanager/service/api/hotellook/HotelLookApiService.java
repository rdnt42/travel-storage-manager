package com.summerdev.travelstoragemanager.service.api.hotellook;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 25.11.2021
 * Time: 22:32
 */
public interface HotelLookApiService {
    /**
     * Информация обо всех отелях в указанном городе
     *
     * @param city      город поиска
     * @param startDate дата заезда
     * @param endDate   дата выезда
     * @return список отелей, соответсующих фильтру
     */
    List<HotelLookHotelResponse> getHotelsResponse(GeoNameData city, Date startDate, Date endDate);
}
