package com.summerdev.travelstoragemanager.response.api.hotellook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelLookPricesResponse {
    @JsonProperty("3")
    private Double smallPrice;

    @JsonProperty("50")
    private Double mediumPrice;

    @JsonProperty("99")
    private Double highPrice;
}
