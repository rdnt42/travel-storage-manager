package com.summerdev.travelstoragemanager.service.travelInfo;

import com.summerdev.travelstoragemanager.entity.HotelInfo;
import com.summerdev.travelstoragemanager.repository.HotelInfoRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 0:09
 */
@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelsInfoServiceImpl implements TravelInfoService<HotelInfo>, CursorService {

    @NonNull HotelInfoRepository hotelInfoRepository;

    @Override
    public List<HotelInfo> getAllActualInfo() {
        return null;
    }

    @Override
    public void updateTravelInfo() {
        log.info("info update");
    }

    @Override
    public Long getFirstCursorId() {
        HotelInfo info = hotelInfoRepository.findFirstByOrderByIdAsc();

        return info == null ? null : info.getId();
    }

    @Override
    public Long getLastCursorId() {
        return null;
    }
}
