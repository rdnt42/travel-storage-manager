package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.entity.train.TutuStation;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.serviceType.HotelLookServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.11.2021
 * Time: 22:36
 */
@RequiredArgsConstructor
@Service
public class HotelCursorServiceImpl implements CursorService, HotelLookServiceType {
    private final TutuStationRepository tutuStationRepository;

    @Override
    public Long getFirstCursorId() {
        TutuStation station = tutuStationRepository.findFirstByOrderByIdAsc();

        return station == null ? null : station.getId();
    }

    @Override
    public Long getNextCursorId(long currCursorId) {
        TutuStation prevStation = tutuStationRepository.findById(currCursorId)
                .orElse(tutuStationRepository.findFirstByIdAfterOrderByIdAsc(currCursorId));

        TutuStation station = tutuStationRepository
                .findFirstByIdAfterAndGeoNameIdNotOrderByIdAsc(currCursorId, prevStation.getGeoNameId());

        return station == null ? null : station.getId();
    }
}
