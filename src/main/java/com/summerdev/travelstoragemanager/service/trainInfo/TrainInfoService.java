package com.summerdev.travelstoragemanager.service.trainInfo;

import com.summerdev.travelstoragemanager.entity.train.TrainInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 15.12.2021
 * Time: 23:56
 */
public interface TrainInfoService {
    int updateOrCreate(List<TrainInfo> trainInfos);
}
