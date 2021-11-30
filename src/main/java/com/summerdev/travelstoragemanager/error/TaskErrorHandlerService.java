package com.summerdev.travelstoragemanager.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.summerdev.travelstoragemanager.response.HotelLookErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.summerdev.travelstoragemanager.error.BusinessLogicException.BusinessError.JSON_PARSE_ERROR_CODE;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 30.11.2021
 * Time: 22:55
 */
@Service
public class TaskErrorHandlerService {
    public void errorHandler(WebClientResponseException e) {

        HttpStatus status = e.getStatusCode();
        if (!e.getResponseBodyAsString().isEmpty()) {

        }

    }

    private HotelLookErrorResponse getErrorResponse(String exceptionMess) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(exceptionMess, HotelLookErrorResponse.class);
        } catch (JsonProcessingException e) {
            throw new BusinessLogicException(JSON_PARSE_ERROR_CODE);
        }

    }
}
