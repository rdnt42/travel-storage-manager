package com.summerdev.travelstoragemanager.service.api.hotellook;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.request.api.hotellook.HotelLookRequest;
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
     * Запрос на API hotellook.ru через travelpayouts
     * https://support.travelpayouts.com/hc/ru/articles/115000343268-API-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D1%85-%D0%BE%D1%82%D0%B5%D0%BB%D0%B5%D0%B9
     *
     * @param request фильтр для поиска отеля в указанном городе
     * @return список отелей, соответсвующих фильтру
     */
    List<HotelLookHotelResponse> getHotelsResponse(HotelLookRequest request);

    /**
     * Информация обо всех отелях в указанном городе
     *
     * @param city      город поиска
     * @param startDate дата заезда
     * @param endDate   дата выезда
     * @return список отелей, соответсующих фильтру
     */
    List<HotelLookHotelResponse> getHotelsInfo(GeoNameData city, Date startDate, Date endDate);
}
