package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 23:48
 */
public interface InfoTaskStateService {
    void enableTask(InfoTask task);

    void disableTask(InfoTask task);

    void disableAndDeleteTask(InfoTask task);
}
