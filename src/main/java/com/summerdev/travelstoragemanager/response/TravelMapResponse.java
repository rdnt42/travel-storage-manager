package com.summerdev.travelstoragemanager.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TravelMapResponse {
    private List<TravelMapItemResponse> travelMapItems = new ArrayList<>();

    public void addItem(TravelMapItemResponse itemResponse) {
        travelMapItems.add(itemResponse);
    }

}
