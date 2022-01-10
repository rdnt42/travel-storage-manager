package com.summerdev.travelstoragemanager.service.hotelInfo;

import com.summerdev.travelstoragemanager.entity.tutu.TutuStation;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.service.CursorService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.11.2021
 * Time: 22:36
 */

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class HotelCursorServiceImpl implements CursorService {
    @NonNull TutuStationRepository tutuStationRepository;

    @Override
    public Long getFirstCursorId() {
        TutuStation station = tutuStationRepository.findFirstByOrderByIdAsc();

        return station == null ? null : station.getId();
    }

    @Override
    public Long getNextCursorId(long currCursorId) {
        TutuStation station = tutuStationRepository.findFirstByIdAfterOrderByIdAsc(currCursorId);

        return station == null ? null : station.getId();
    }
}
