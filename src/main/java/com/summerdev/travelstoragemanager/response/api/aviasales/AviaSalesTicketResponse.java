package com.summerdev.travelstoragemanager.response.api.aviasales;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.DateFormat;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaSalesTicketResponse {

    private double price;
    private String airlane;
    private String flight_number;
    private DateFormat departure_at;
    private DateFormat return_at;
    private DateFormat expires_at;

    public AviaSalesTicketResponse() {
    }

    public AviaSalesTicketResponse(double price, String airlane, String flight_number, DateFormat departure_at, DateFormat return_at, DateFormat expires_at) {
        this.price = price;
        this.airlane = airlane;
        this.flight_number = flight_number;
        this.departure_at = departure_at;
        this.return_at = return_at;
        this.expires_at = expires_at;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAirlane() {
        return airlane;
    }

    public void setAirlane(String airlane) {
        this.airlane = airlane;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public DateFormat getDeparture_at() {
        return departure_at;
    }

    public void setDeparture_at(DateFormat departure_at) {
        this.departure_at = departure_at;
    }

    public DateFormat getReturn_at() {
        return return_at;
    }

    public void setReturn_at(DateFormat return_at) {
        this.return_at = return_at;
    }

    public DateFormat getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(DateFormat expires_at) {
        this.expires_at = expires_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AviaSalesTicketResponse)) return false;
        AviaSalesTicketResponse that = (AviaSalesTicketResponse) o;
        return Double.compare(that.price, price) == 0 && Objects.equals(airlane, that.airlane) && Objects.equals(flight_number, that.flight_number) && Objects.equals(departure_at, that.departure_at) && Objects.equals(return_at, that.return_at) && Objects.equals(expires_at, that.expires_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, airlane, flight_number, departure_at, return_at, expires_at);
    }

    @Override
    public String toString() {
        return "AviaSalesTicketResponse{" +
                "price=" + price +
                ", airlane='" + airlane + '\'' +
                ", flight_number='" + flight_number + '\'' +
                ", departure_at=" + departure_at +
                ", return_at=" + return_at +
                ", expires_at=" + expires_at +
                '}';
    }

    //  "price": 40669,
//  "airline": "SU",
//  "flight_number": 278,
//  "departure_at": "2021-11-12T04:50:00Z",
//  "return_at": "2021-12-08T15:25:00Z",
//  "expires_at": "2021-04-04T04:24:40Z"
}
