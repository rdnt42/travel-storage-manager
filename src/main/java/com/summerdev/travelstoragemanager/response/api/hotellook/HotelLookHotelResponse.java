package com.summerdev.travelstoragemanager.response.api.hotellook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelLookHotelResponse {
    private HotelLookLocationResponse location;
    private Double priceAvg;
    private HotelLookPricesResponse pricePercentile;
    private String hotelName;
    private Long stars;
    private Long hotelId;
    private Double priceFrom;
}
