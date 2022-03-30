package com.summerdev.travelstoragemanager.response.api.aviasales;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AviaSalesPriceMapResponse {

    // — prices available to affiliates
    @JsonProperty("show_to_affiliates")
    private boolean showToAffiliates;

    // — trip class (1 — business class, 0 — standard).
    @JsonProperty("trip_class")
    private boolean tripClass;

    // — IATA code for the destination.
    private String destination;

    // — departure date.
    @JsonProperty("depart_date")
    private String departDate;

    // — return date.
    @JsonProperty("return_date")
    private String returnDate;

    // — number of stops.
    @JsonProperty("number_of_changes")
    private int numberOfChanges;

    // — flight cost.
    private int value;

    // — time and date of query creating (in unix timestamp format).
    @JsonProperty("created_at")
    private long createdAt;

    // — (time-to-live) — time and date at which the offer is arriving (in unix timestamp format).
    private long ttl;

    // — distance between the departure and arrival points.
    private String distance;

    // — equals 0 or 1. 0 — price was found long ago and may be out of date. 1 — price was found recently and is current.
    private boolean actual;
}
