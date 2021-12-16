package com.summerdev.travelstoragemanager.response.api.aviasales.bkp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AviaSalesTicketsResponse {

    private List<AviaSalesTicketResponse> tickets = new ArrayList<>();

    public AviaSalesTicketsResponse(List<AviaSalesTicketResponse> tickets) {
        this.tickets = tickets;
    }

    public List<AviaSalesTicketResponse> getTickets() {
        return tickets;
    }

    public void setTickets(List<AviaSalesTicketResponse> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AviaSalesTicketsResponse)) return false;
        AviaSalesTicketsResponse that = (AviaSalesTicketsResponse) o;
        return Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tickets);
    }

    @Override
    public String toString() {
        return "AviaSalesTicketsResponse{" +
                "tickets=" + tickets +
                '}';
    }
}
