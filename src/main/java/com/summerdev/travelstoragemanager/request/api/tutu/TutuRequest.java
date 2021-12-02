package com.summerdev.travelstoragemanager.request.api.tutu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TutuRequest {
    @JsonProperty("term")
    private int departureStation;

    @JsonProperty("term2")
    private int arrivalStation;

    public TutuRequest(int departureStation, int arrivalStation) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

}
