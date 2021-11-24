package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.service.task.factory.RunnableTask;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static com.summerdev.travelstoragemanager.service.task.InfoTaskServiceImpl.infoTasksMap;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 23:48
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InfoTaskStateServiceImpl implements InfoTaskStateService {

    @NonNull InfoTaskRepository infoTaskRepository;

    @Override
    public void enableTask(InfoTask task) {
        RunnableTask runnableTask = infoTasksMap.get(task.getId());
        if (runnableTask == null) {
            throw new NullPointerException("Task wth id: " + task.getId() +
                    " not found");
        }

        runnableTask.startTask();
    }

    @Override
    public void disableTask(InfoTask task) {
        RunnableTask runnableTask = infoTasksMap.get(task.getId());
        if (runnableTask == null) {
            throw new NullPointerException("Task wth id: " + task.getId() +
                    " not found");
        }

        runnableTask.stopTask();
    }

    @Override
    public void disableAndDeleteTask(InfoTask task) {
        disableTask(task);
        infoTaskRepository.delete(task);
    }

}
