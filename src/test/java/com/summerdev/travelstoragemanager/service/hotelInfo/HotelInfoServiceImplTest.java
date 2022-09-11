package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.train.TrainInfo;
import com.summerdev.travelstoragemanager.repository.HotelInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 11.09.2022
 * Time: 16:23
 */
@ExtendWith(MockitoExtension.class)
class HotelInfoServiceImplTest {
    @InjectMocks
    private HotelInfoServiceImpl hotelInfoService;

    @Mock
    private HotelInfoRepository hotelInfoRepository;

    @Test
    void updateOrCreateEmptyEntitiesError() {
        when(hotelInfoRepository.saveAll(Collections.emptyList()))
                .thenThrow(IllegalArgumentException.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                hotelInfoService.updateOrCreate(Collections.emptyList()));

        assertEquals(IllegalArgumentException.class, exception.getClass());
    }

    @Test
    void updateOrCreateSuccess() {
        when(hotelInfoRepository.saveAll(anyCollection()))
                .then(AdditionalAnswers.returnsFirstArg());
        when(hotelInfoRepository.findById(anyLong()))
                .thenReturn(Optional.of(new HotelInfo()));

        int resultCount = hotelInfoService.updateOrCreate(getTwoDiffInfos());

        assertEquals(2, resultCount);
    }

    private List<HotelInfo> getTwoDiffInfos() {
        HotelInfo hotelInfo1 = new HotelInfo();
        hotelInfo1.setId(1L);

        HotelInfo hotelInfo2 = new HotelInfo();
        hotelInfo1.setId(2L);

        return List.of(hotelInfo1, hotelInfo2);
    }
}