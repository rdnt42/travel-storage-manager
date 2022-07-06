package com.summerdev.travelstoragemanager.service.api.tutu;

import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.response.api.tutu.TutuTrainsResponse;
import com.summerdev.travelstoragemanager.service.trainInfo.TrainApiErrorHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 26.06.2022
 * Time: 19:53
 */
@ExtendWith(MockitoExtension.class)
class TutuApiServiceImplTest {
    @InjectMocks
    private TutuApiServiceImpl tutuApiService;

    @Mock
    private WebClient webClient;

    @Mock
    private TrainApiErrorHandlerService trainApiErrorHandlerService;
    @Mock
    private WebClient.RequestHeadersUriSpec<?> uriSpecMock;

    @Test
    void getTrainsResponseEmptyParamsFailed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                tutuApiService.getTrainsResponse(null, null));

        assertNotNull(exception);
    }

    @Test
    void getTrainsResponseAnyExceptionRequestSuccess() {
        initWebClient();
        doNothing().when(trainApiErrorHandlerService).handleError(any(), any());

        TutuTrainsResponse response = tutuApiService
                .getTrainsResponse(getFilledStation(1), getFilledStation(2));

        assertTrue(response.getTrips().isEmpty());
    }

    private TutuStation getFilledStation(long id) {
        TutuStation station = new TutuStation();
        station.setId(id);

        return station;
    }

    private void initWebClient() {
        doReturn(uriSpecMock)
                .when(webClient).get();

        ReflectionTestUtils.setField(tutuApiService, "tutuUrl", "http://test");

        when(uriSpecMock.uri(any(URI.class)))
                .thenThrow(WebClientResponseException.class);
    }
}