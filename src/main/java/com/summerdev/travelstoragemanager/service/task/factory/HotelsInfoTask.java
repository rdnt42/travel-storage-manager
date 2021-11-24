package com.summerdev.travelstoragemanager.service.task.factory;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.service.task.InfoTaskStateService;
import com.summerdev.travelstoragemanager.service.travelInfo.HotelsInfoServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:30
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HotelsInfoTask extends RunnableTask {
    HotelsInfoServiceImpl hotelsInfoService;
    InfoTaskStateService infoTaskStateService;

    public HotelsInfoTask(InfoTask task, HotelsInfoServiceImpl hotelsInfoService, InfoTaskStateService infoTaskStateService) {
        super(task);
        this.hotelsInfoService = hotelsInfoService;
        this.infoTaskStateService = infoTaskStateService;
    }


    @Override
    public void run() {
        hotelsInfoService.updateTravelInfo();
        infoTaskStateService.disableAndDeleteTask(task);
    }
}
