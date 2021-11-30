package com.summerdev.travelstoragemanager.response;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 01.12.2021
 * Time: 0:03
 */
@Data
public class HotelLookErrorResponse {
    private Long errorCode;
    private String status;
    private String location;
}
