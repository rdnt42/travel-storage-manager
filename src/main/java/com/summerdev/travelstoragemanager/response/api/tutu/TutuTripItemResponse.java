package com.summerdev.travelstoragemanager.response.api.tutu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TutuTripItemResponse {
    private String name;

    @JsonDeserialize(as = Long.class)
    private Long departureStation;

    @JsonDeserialize(as = Long.class)
    private Long arrivalStation;

    private String runDepartureStation;
    private String runArrivalStation;
    private String departureTime;
    private String arrivalTime;
    private String trainNumber;

    @JsonDeserialize(as = Long.class)
    private Long travelTimeInSeconds;
    private List<TutuRailwayCarriageResponse> categories = new ArrayList<>();

}
