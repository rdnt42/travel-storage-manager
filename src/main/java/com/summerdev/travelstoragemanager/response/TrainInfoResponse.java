package com.summerdev.travelstoragemanager.response;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 14:45
 */
@Data
public class TrainInfoResponse {
    private GeoNameData departureCity;
    private GeoNameData arrivalCity;
    private Long travelTime;
    private String categoryName;
    private Long cost;

}
