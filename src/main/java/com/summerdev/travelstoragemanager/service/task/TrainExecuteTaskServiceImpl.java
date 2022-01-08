package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.task.factory.RunnableTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrainExecuteTaskServiceImpl implements ExecuteTaskService, CursorService {
    @Override
    public Long getFirstCursorId() {
        return null;
    }

    @Override
    public Long getNextCursorId(long currCursorId) {
        return null;
    }

    @Override
    public void executeTask(RunnableTask runnableTask) {
        log.info("execute");
    }
}
