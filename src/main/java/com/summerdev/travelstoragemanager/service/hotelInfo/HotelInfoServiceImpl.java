package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.repository.HotelInfoRepository;
import com.summerdev.travelstoragemanager.serviceType.HotelLookServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 0:09
 */
@RequiredArgsConstructor
@Service
public class HotelInfoServiceImpl implements HotelInfoService, HotelLookServiceType {

    private final HotelInfoRepository hotelInfoRepository;

    @Transactional
    @Override
    public int updateOrCreate(List<HotelInfo> hotelInfos) {
        Map<Long, HotelInfo> newItems = hotelInfos.stream()
                .collect(Collectors.toMap(HotelInfo::getId, Function.identity()));

        List<HotelInfo> itemsToUpdate = hotelInfoRepository.findAllById(newItems.keySet());

        for (HotelInfo itemToUpdate : itemsToUpdate) {
            HotelInfo newItem = newItems.get(itemToUpdate.getId());

            updateItem(itemToUpdate, newItem);
            newItems.put(itemToUpdate.getId(), itemToUpdate);
        }

        return hotelInfoRepository.saveAll(newItems.values()).size();
    }

    private void updateItem(HotelInfo info, HotelInfo newInfo) {
        info.setHotelName(newInfo.getHotelName());
        info.setCity(newInfo.getCity());
        info.setStars(newInfo.getStars());
        info.addNewPrices(newInfo.getHotelPrices());

        info.setLastUpdate(new Date());
    }
}
