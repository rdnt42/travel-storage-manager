package com.summerdev.travelstoragemanager.response.api.hotellook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HotelLookHotelResponse {
    private HotelLookLocationResponse location;
    private Double priceAvg;
    private HotelLookPricesResponse pricePercentile;
    private String hotelName;
    private Long stars;
    private Long hotelId;
    private Double priceFrom;
}
