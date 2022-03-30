package com.summerdev.travelstoragemanager.response.api.aviasales.bkp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaSalesTicketResponse {

    private double price;

    private String airlane;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("departure_at")
    private DateFormat departureAt;

    @JsonProperty("return_at")
    private DateFormat returnAt;

    @JsonProperty("expires_at")
    private DateFormat expiresAt;

}
