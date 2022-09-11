package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import com.summerdev.travelstoragemanager.repository.HotelInfoRepository;
import com.summerdev.travelstoragemanager.serviceType.HotelLookServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<HotelInfo> listForSave = new ArrayList<>();
        for (HotelInfo hotelInfo : hotelInfos) {
            HotelInfo updatedItem = updateOrCreateItem(hotelInfo);
            listForSave.add(updatedItem);
        }

        return hotelInfoRepository.saveAll(listForSave).size();
    }

    private HotelInfo updateOrCreateItem(HotelInfo newInfo) {
        HotelInfo hotelInfo = hotelInfoRepository.findById(newInfo.getId())
                .orElse(newInfo);

        hotelInfo.setHotelName(newInfo.getHotelName());
        hotelInfo.setCity(newInfo.getCity());
        hotelInfo.setStars(newInfo.getStars());
        hotelInfo.addNewPrices(newInfo.getHotelPrices());

        hotelInfo.setLastUpdate(new Date());

        return hotelInfo;
    }
}
