package com.summerdev.travelstoragemanager.service.task.factory;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.service.hotelInfo.HotelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.task.InfoTaskStateService;
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
    HotelInfoUpdaterService hotelInfoUpdaterService;
    InfoTaskStateService infoTaskStateService;

    public HotelsInfoTask(InfoTask task, InfoTaskStateService infoTaskStateService,
                          HotelInfoUpdaterService hotelInfoUpdaterService) {
        super(task);
        this.hotelInfoUpdaterService = hotelInfoUpdaterService;
        this.infoTaskStateService = infoTaskStateService;
    }


    @Override
    public void run() {
        hotelInfoUpdaterService.updateTravelInfo();
        infoTaskStateService.disableAndDeleteTask(task);
    }
}
