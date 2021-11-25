package com.summerdev.travelstoragemanager.service.api.tutu;

public interface TutuService {
    /**
     * Запрос на API tutu.ru через travelpayouts
     * https://support.travelpayouts.com/hc/ru/articles/360020147791-API-%D0%BE%D1%82-Tutu-ru
     *
     * @param departureStation станция отправления, код РЖД
     * @param arrivalStation   станция прибытия, код РЖД
     * @return список поездов по данному направлению
     */
//    TutuTrainsResponse getTrainsResponse(TutuStation departureStation, TutuStation arrivalStation);

    /**
     * Информация о поездах по всем направлениям из пункта отправления
     *
     * @param departureCityName название города отправления
     * @return список поездов по всем направленям, доступным из пункта отправления
     */
//    List<TutuTrainsResponse> getTrainsInfo(String departureCityName);
//
//    List<TutuTrainsResponse> getTrainsInfo(GeoName departureCity);
}
