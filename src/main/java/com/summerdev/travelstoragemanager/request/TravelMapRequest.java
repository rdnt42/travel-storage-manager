package com.summerdev.travelstoragemanager.request;

import lombok.Data;

@Data
public class TravelMapRequest {
    private String departureCity;
    private Long maxCost;
}
