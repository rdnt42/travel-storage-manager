package com.summerdev.travelstoragemanager.service.api.aviasales;

import com.summerdev.travelstoragemanager.response.api.aviasales.AviaSalesPriceMapResponse;

import java.util.Date;
import java.util.List;

public interface AviasalesApiService {

    // https://support.travelpayouts.com/hc/en-us/articles/203755406-Price-Map-API
    //
    // Cудя по всему это карта низких цен на aviasales
    //
    List<AviaSalesPriceMapResponse> getPriceMap(String iataOrigin, Date fromDate, Integer durationDaysMin, Integer durationDaysMax);

}
