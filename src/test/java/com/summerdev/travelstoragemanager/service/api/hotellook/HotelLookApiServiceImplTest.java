package com.summerdev.travelstoragemanager.service.api.hotellook;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.request.api.hotellook.HotelLookRequest;
import com.summerdev.travelstoragemanager.response.api.hotellook.HotelLookHotelResponse;
import com.summerdev.travelstoragemanager.service.hotelInfo.HotelApiErrorHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.URI;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 25.06.2022
 * Time: 0:12
 */
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "/api/api-url.properties")
class HotelLookApiServiceImplTest {

    @InjectMocks
    private HotelLookApiServiceImpl hotelLookApiService;

    @Mock
    private WebClient webClient;

    @Mock
    private HotelApiErrorHandlerService hotelTaskErrorHandlerService;


    @Test
    void getHotelsResponseEmptyRequestFailed() {

        Exception thrown = assertThrows(IllegalArgumentException.class, () ->
                hotelLookApiService.getHotelsResponse(null, null, null));

        assertNotNull(thrown);
    }

    @Test
    void getHotelsResponseAnyExceptionRequestSuccess() {
        initWebClient();
        doNothing().when(hotelTaskErrorHandlerService).handleError(any(), any());

        HotelLookRequest request = getFilledRequest();
        request.setLocation(null);

        List<HotelLookHotelResponse> responses = hotelLookApiService
                .getHotelsResponse(getGeoNameData(), request.getCheckIn(), request.getGetCheckOut());

        assertTrue(responses.isEmpty());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initWebClient() {
        WebClient.RequestHeadersUriSpec uriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
        when(webClient.get())
                .thenReturn(uriSpecMock);

        ReflectionTestUtils.setField(hotelLookApiService, "hotelValue", "http://test");

        when(uriSpecMock.uri(any(URI.class)))
                .thenThrow(WebClientResponseException.class);
    }

    private GeoNameData getGeoNameData() {
        GeoNameData data = new GeoNameData();
        data.setGeoNameRu("");

        return data;
    }
    private HotelLookRequest getFilledRequest() {
        return new HotelLookRequest(getGeoNameData().getGeoNameRu(), new Date(), new Date());
    }
}