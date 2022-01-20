package com.summerdev.travelstoragemanager.service.api.tutu;

import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.request.api.tutu.TutuRequest;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;

public interface TutuApiService {

    /**
     * @param request фильтр для поиска поездов по указанному маршруту
     * @return список поездов по данному направлению
     */
    TutuTrainsResponse getTrainsResponse(TutuRequest request);

    /**
     * Запрос на API tutu.ru через travelpayouts
     * https://support.travelpayouts.com/hc/ru/articles/360020147791-API-%D0%BE%D1%82-Tutu-ru
     *
     * @param departureStation станция отправления, код РЖД
     * @param arrivalStation   станция прибытия, код РЖД
     * @return список поездов по данному направлению
     */
    TutuTrainsResponse getTrainsResponse(TutuStation departureStation, TutuStation arrivalStation);
}
