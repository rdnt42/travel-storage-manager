package com.summerdev.travelstoragemanager.service.api.tutu;

import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;

public interface TutuApiService {

    /**
     * поиск поездов по указанному маршруту
     * @param departureStation станция отправления, код РЖД
     * @param arrivalStation   станция прибытия, код РЖД
     * @return список поездов по данному направлению
     */
    TutuTrainsResponse getTrainsResponse(TutuStation departureStation, TutuStation arrivalStation);
}
