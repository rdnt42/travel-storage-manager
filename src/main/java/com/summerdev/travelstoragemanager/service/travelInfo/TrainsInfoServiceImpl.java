package com.summerdev.travelstoragemanager.service.travelInfo;

import com.summerdev.travelstoragemanager.entity.TrainInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 22:28
 */
@Service
public class TrainsInfoServiceImpl implements TravelInfoService<TrainInfo>, CursorService {

    @Override
    public Long getFirstCursorId() {
        return null;
    }

    @Override
    public Long getLastCursorId() {
        return null;
    }

    @Override
    public List<TrainInfo> getAllActualInfo() {
        return null;
    }

    @Override
    public void updateTravelInfo() {

    }
}
