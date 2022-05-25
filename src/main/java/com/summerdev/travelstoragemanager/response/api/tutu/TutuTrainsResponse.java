package com.summerdev.travelstoragemanager.response.api.tutu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TutuTrainsResponse {
    List<TutuTripItemResponse> trips = new ArrayList<>();
}
