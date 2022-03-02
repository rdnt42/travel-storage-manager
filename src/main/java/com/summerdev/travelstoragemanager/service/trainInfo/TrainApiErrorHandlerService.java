package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.error.BusinessLogicException;
import com.summerdev.travelstoragemanager.request.api.tutu.TutuRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 11.01.2022
 * Time: 21:31
 */
@Service
public class TrainApiErrorHandlerService {
    public void handleError(WebClientResponseException e, TutuRequest request) {
        HttpStatus status = e.getStatusCode();

        if (status == HttpStatus.TOO_MANY_REQUESTS) {
            throw new BusinessLogicException(BusinessLogicException.BusinessError.TOO_MANY_REQUESTS_ERROR);
        } else {
            throw new BusinessLogicException(BusinessLogicException.BusinessError.UNKNOWN_ERROR,
                    "Unknown Http status: " + status.value() +
                    " request departureStation: " + request.getDepartureStation() +
                    ", arrivalStation: " + request.getArrivalStation());
        }
    }
}
