package com.summerdev.travelstoragemanager.service;

import com.summerdev.travelstoragemanager.service.task.InfoTaskService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 25.01.2022
 * Time: 19:41
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service
public class InitService {
    @NonNull InfoTaskService infoTaskService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        infoTaskService.initTasks();
    }
}
