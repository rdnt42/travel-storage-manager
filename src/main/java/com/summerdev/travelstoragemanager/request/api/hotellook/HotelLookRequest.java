package com.summerdev.travelstoragemanager.request.api.hotellook;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class HotelLookRequest {
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String location;
    private Date checkIn;
    private Date getCheckOut;
    private int adults = 1;
    private int limit = 4;
    private String currency = "rub";

    // Optional
    private int locationId;
    private String hotel;
    private int hotelId;
    private int children;
    private String token;

    public HotelLookRequest(String location, Date checkIn, Date getCheckOut) {
        this.location = location;
        this.checkIn = checkIn;
        this.getCheckOut = getCheckOut;
    }

    public String getCheckInFormatted() {
        return simpleDateFormat.format(checkIn);
    }

    public String getCheckOutFormatted() {
        return simpleDateFormat.format(getCheckOut);
    }
}
