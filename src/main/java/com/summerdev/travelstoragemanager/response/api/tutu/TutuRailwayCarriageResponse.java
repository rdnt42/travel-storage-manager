package com.summerdev.travelstoragemanager.response.api.tutu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TutuRailwayCarriageResponse {
    private String type;
    private int price;
}
