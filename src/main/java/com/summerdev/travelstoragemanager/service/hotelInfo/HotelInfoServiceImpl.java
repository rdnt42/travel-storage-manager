package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.entity.hotel.HotelPrice;
import com.summerdev.travelstoragemanager.repository.HotelInfoRepository;
import com.summerdev.travelstoragemanager.repository.HotelPriceRepository;
import com.summerdev.travelstoragemanager.service.travelInfo.CursorService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 0:09
 */
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class HotelInfoServiceImpl implements HotelInfoService, CursorService {

    @NonNull HotelInfoRepository hotelInfoRepository;
    @NonNull HotelPriceRepository hotelPriceRepository;

    @Override
    public Long getFirstCursorId() {
        HotelInfo info = hotelInfoRepository.findFirstByOrderByIdAsc();

        return info == null ? null : info.getId();
    }

    @Override
    public Long getLastCursorId() {
        return null;
    }

    @Transactional
    public void deleteAndCreate(List<HotelInfo> hotelInfos) {
        List<Long> ids = hotelInfos.stream()
                .map(HotelInfo::getId)
                .collect(Collectors.toList());
        List<HotelInfo> infosToDelete = hotelInfoRepository.findAllById(ids);

        List<Long> pricesToDelete = infosToDelete.parallelStream()
                .map(HotelInfo::getHotelPrices)
                .flatMap(List::stream)
                .map(HotelPrice::getId)
                .collect(Collectors.toList());

        // it`s don`t work. change to update or create
        hotelPriceRepository.deleteByIdIn(pricesToDelete);
        hotelPriceRepository.flush();

        hotelInfoRepository.deleteByIdIn(ids);
        hotelInfoRepository.flush();

        hotelInfoRepository.saveAll(hotelInfos);
    }

}
