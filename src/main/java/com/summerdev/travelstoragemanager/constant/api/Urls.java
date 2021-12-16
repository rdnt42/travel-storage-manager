package com.summerdev.travelstoragemanager.constant.api;

public class Urls {

    private Urls() {
    }

    // https://support.travelpayouts.com/hc/ru/articles/360020147791-API-%D0%BE%D1%82-Tutu-ru
    public static final String URL_TUTU_GET_TRAINS = "https://suggest.travelpayouts.com/search?service=tutu_trains";

    // https://support.travelpayouts.com/hc/ru/articles/115000343268-API-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D1%85-%D0%BE%D1%82%D0%B5%D0%BB%D0%B5%D0%B9#price
    public static final String URL_HOTEL_LOOK_GET_HOTELS = "http://engine.hotellook.com/api/v2/cache.json";

    //"https://api.travelpayouts.com/v1/prices/cheap?origin=MOW&destination=HKT&depart_date=2016-11&return_date=2016-12&token";
//    public static final String URL_AVIASALES_GET_CHEAP_TICKETS = "https://api.travelpayouts.com/v1/prices/cheap?";

}