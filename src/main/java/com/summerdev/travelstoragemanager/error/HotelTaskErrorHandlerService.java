package com.summerdev.travelstoragemanager.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.summerdev.travelstoragemanager.request.api.hotellook.HotelLookRequest;
import com.summerdev.travelstoragemanager.response.HotelLookErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.summerdev.travelstoragemanager.error.BusinessLogicException.*;
import static com.summerdev.travelstoragemanager.error.HotelExecuteException.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 30.11.2021
 * Time: 22:55
 */
@Service
public class HotelTaskErrorHandlerService {
    public void errorHandler(WebClientResponseException e, HotelLookRequest request) {
        HttpStatus status = e.getStatusCode();

        if (status == HttpStatus.BAD_REQUEST) {
            badRequestHandler(e.getResponseBodyAsString(), request);
        } else if (status == HttpStatus.TOO_MANY_REQUESTS) {
            throw new BusinessLogicException(BusinessError.TOO_MANY_REQUESTS_ERROR);
        } else {
            throw new BusinessLogicException(BusinessError.UNKNOWN_ERROR,
                    "Unknown Http status: " + status.value());
        }
    }

    private HotelLookErrorResponse getErrorResponse(String exceptionMess) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(exceptionMess, HotelLookErrorResponse.class);
        } catch (JsonProcessingException e) {
            throw new BusinessLogicException(BusinessError.JSON_PARSE_ERROR_CODE, e.getMessage());
        }

    }

    private void badRequestHandler(String errorText, HotelLookRequest request) {
        HotelLookErrorResponse response = getErrorResponse(errorText);
        if (response.getErrorCode() == 2) {
            throw new HotelExecuteException(HotelError.LOCATION_NOT_FOUND_ERROR,
                    ". Location: " + request.getLocation());
        }

        throw new HotelExecuteException(HotelError.UNKNOWN_ERROR,
                "Unknown error code: " + response.getErrorCode());
    }
}
