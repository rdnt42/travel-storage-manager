package com.summerdev.travelstoragemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 22:23
 */
@Configuration
public class ThreadPoolTaskSchedulerConfig {
    public static final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        threadPoolTaskScheduler.setPoolSize(100);
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTask");
        threadPoolTaskScheduler.initialize();

        return threadPoolTaskScheduler;
    }
}
