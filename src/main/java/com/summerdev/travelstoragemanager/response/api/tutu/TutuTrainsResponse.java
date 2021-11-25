package com.summerdev.travelstoragemanager.response.api.tutu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TutuTrainsResponse {
    List<TutuTripItemResponse> trips = new ArrayList<>();
}
