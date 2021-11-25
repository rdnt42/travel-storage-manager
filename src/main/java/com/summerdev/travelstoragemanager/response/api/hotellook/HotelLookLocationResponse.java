package com.summerdev.travelstoragemanager.response.api.hotellook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HotelLookLocationResponse {
    private String country;
    private HotelLookCoordinateResponse geo;
    private String state;
    private String name;
}
