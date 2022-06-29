package com.summerdev.travelstoragemanager.service.task.execute;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.service.task.runnable.RunnableTask;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.06.2022
 * Time: 23:01
 */
public interface ExecuteTaskUpdaterService {
    int updateTravelInfo(RunnableTask runnableTask, InfoTask task);

    void updateNextCursor(RunnableTask runnableTask, InfoTask task);
}
