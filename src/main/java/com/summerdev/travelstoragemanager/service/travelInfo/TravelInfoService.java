package com.summerdev.travelstoragemanager.service.travelInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 22.11.2021
 * Time: 20:54
 */
public interface TravelInfoService<T> {
    /**
     * @return получить список актуальных данных о сервисе
     */
    List<T> getAllActualInfo();

}
