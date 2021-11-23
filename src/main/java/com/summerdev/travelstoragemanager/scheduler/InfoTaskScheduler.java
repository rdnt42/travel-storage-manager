package com.summerdev.travelstoragemanager.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:22
 */
public class InfoTaskScheduler {

    @Scheduled(cron = "0 0 0 * * *")
    public void createTasksInfo() {

    }
}
