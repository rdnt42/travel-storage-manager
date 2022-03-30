package com.summerdev.travelstoragemanager.response.api.aviasales.bkp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaSalesDataResponse {
    private AviaSalesTicketsResponse aviaSalesTicketsResponse;
}
