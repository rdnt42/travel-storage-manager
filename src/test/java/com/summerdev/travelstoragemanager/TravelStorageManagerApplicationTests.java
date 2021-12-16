package com.summerdev.travelstoragemanager;

import com.summerdev.travelstoragemanager.response.api.aviasales.AviaSalesPriceMapResponse;
import com.summerdev.travelstoragemanager.service.api.aviasales.AviasalesApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class TravelStorageManagerApplicationTests {

    public static final String iataMoscow = "MOW";
    public static final String iataPhucket = "HKT";

    @Autowired
    private AviasalesApiService aviasalesApi;

    @Test
    void contextLoads() {
        assert (aviasalesApi != null);
    }

    @Test
    void getAviasalesTickets() {
        // Почему-то не выдаёт дистанцию и рейсы бизнес класса
        List<AviaSalesPriceMapResponse> resp = aviasalesApi.getPriceMap(iataMoscow, new Date(), 5, 10);
        int i=0;
    }

}
