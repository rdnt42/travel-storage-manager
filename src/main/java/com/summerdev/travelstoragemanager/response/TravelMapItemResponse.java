package com.summerdev.travelstoragemanager.response;

import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.TrainInfo;
import lombok.Data;

@Data
public class TravelMapItemResponse {
    private TrainInfo trainInfo;

    private HotelInfo hotelInfo;

    public TravelMapItemResponse() {
    }

    public TravelMapItemResponse(TrainInfo trainInfo, HotelInfo hotelInfo) {
        this.trainInfo = trainInfo;
        this.hotelInfo = hotelInfo;
    }
}
