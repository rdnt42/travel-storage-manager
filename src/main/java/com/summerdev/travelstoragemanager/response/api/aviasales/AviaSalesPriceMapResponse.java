package com.summerdev.travelstoragemanager.response.api.aviasales;

public class AviaSalesPriceMapResponse {

    // — prices available to affiliates
    private boolean show_to_affiliates;
    // — trip class (1 — business class, 0 — standard).
    private boolean trip_class;
    // — IATA code for the destination.
    private String destination;
    // — departure date.
    private String depart_date;
    // — return date.
    private String return_date;
    // — number of stops.
    private int number_of_changes;
    // — flight cost.
    private int value;
    // — time and date of query creating (in unix timestamp format).
    private long created_at;
    // — (time-to-live) — time and date at which the offer is arriving (in unix timestamp format).
    private long ttl;
    // — distance between the departure and arrival points.
    private String distance;
    // — equals 0 or 1. 0 — price was found long ago and may be out of date. 1 — price was found recently and is current.
    private boolean actual;

    public boolean isShow_to_affiliates() {
        return show_to_affiliates;
    }

    public void setShow_to_affiliates(boolean show_to_affiliates) {
        this.show_to_affiliates = show_to_affiliates;
    }

    public boolean getTrip_class() {
        return trip_class;
    }

    public void setTrip_class(boolean trip_class) {
        this.trip_class = trip_class;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepart_date() {
        return depart_date;
    }

    public void setDepart_date(String depart_date) {
        this.depart_date = depart_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public int getNumber_of_changes() {
        return number_of_changes;
    }

    public void setNumber_of_changes(int number_of_changes) {
        this.number_of_changes = number_of_changes;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }
}
