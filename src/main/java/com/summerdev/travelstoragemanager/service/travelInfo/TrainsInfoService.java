package com.summerdev.travelstoragemanager.service.travelInfo;

import com.summerdev.travelstoragemanager.entity.TrainInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 15.12.2021
 * Time: 23:56
 */
public interface TrainsInfoService {
    int updateOrCreate(List<TrainInfo> trainInfos);
}
