package com.summerdev.travelstoragemanager.request.api.aviasales;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AviaSalesRequest {

    @JsonProperty("origin")
    private String departureStation;

    @JsonProperty("destination")
    private String arrivalStation;

    @JsonProperty("depart_date")
    private String departDate;

    @JsonProperty("return_date")
    private String returnDate;

    public AviaSalesRequest() {
    }

    public AviaSalesRequest(String departureStation, String arrivalStation, String departDate) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departDate = departDate;
    }

    public AviaSalesRequest(String departureStation, String arrivalStation, String departDate, String returnDate) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departDate = departDate;
        this.returnDate = returnDate;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AviaSalesRequest)) return false;
        AviaSalesRequest that = (AviaSalesRequest) o;
        return Objects.equals(departureStation, that.departureStation) && Objects.equals(arrivalStation, that.arrivalStation) && Objects.equals(departDate, that.departDate) && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureStation, arrivalStation, departDate, returnDate);
    }

    @Override
    public String toString() {
        return "AviaSalesRequest{" +
                "departureStation='" + departureStation + '\'' +
                ", arrivalStation='" + arrivalStation + '\'' +
                ", depart_date=" + departDate +
                ", return_date=" + returnDate +
                '}';
    }
}
