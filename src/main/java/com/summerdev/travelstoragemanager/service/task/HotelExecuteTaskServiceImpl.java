package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import com.summerdev.travelstoragemanager.entity.tutu.TutuStation;
import com.summerdev.travelstoragemanager.error.BusinessLogicException;
import com.summerdev.travelstoragemanager.error.HotelExecuteException;
import com.summerdev.travelstoragemanager.repository.InfoTaskRepository;
import com.summerdev.travelstoragemanager.repository.TutuStationRepository;
import com.summerdev.travelstoragemanager.service.hotelInfo.HotelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.task.factory.RunnableTask;
import com.summerdev.travelstoragemanager.service.travelInfo.CursorService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.summerdev.travelstoragemanager.error.HotelExecuteException.*;

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
public class HotelExecuteTaskServiceImpl implements ExecuteTaskService, CursorService {
    @NonNull HotelInfoUpdaterService hotelInfoUpdaterService;
    @NonNull InfoTaskRepository infoTaskRepository;
    @NonNull TutuStationRepository tutuStationRepository;

    @Override
    public void executeTask(RunnableTask runnableTask) {
        while (!runnableTask.getFuture().isCancelled()) {
            InfoTask task = infoTaskRepository.findById(runnableTask.getTaskId())
                    .orElseThrow(() -> new NullPointerException("Task with id: " + runnableTask.getTaskId() +
                            " not found"));
            Long cursor = task.getCursorId();

            if (cursor == null) break;

            updateTravelInfoWithErrorHandler(cursor, task);

            Long nextCursor = getNextCursorId(cursor);
            task.setCursorId(nextCursor);
            infoTaskRepository.save(task);
        }
    }

    private void updateTravelInfoWithErrorHandler(Long cursor, InfoTask task) {
        try {
            int updatedCount = hotelInfoUpdaterService.updateTravelInfo(cursor);
            log.info("Update {} records for task id {}, cursor id {}", updatedCount, task.getId(), task.getCursorId());
        } catch (HotelExecuteException e) {
            if (e.getCode() == HotelError.LOCATION_NOT_FOUND_ERROR.getCode()) {
                log.warn("Some expected error in task " + task.getId() +
                        ", message: " + e.getMessage());
            }
        }
    }

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
