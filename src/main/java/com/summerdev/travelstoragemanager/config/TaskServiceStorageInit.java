package com.summerdev.travelstoragemanager.config;

import com.summerdev.travelstoragemanager.service.CursorService;
import com.summerdev.travelstoragemanager.service.TravelInfoUpdaterService;
import com.summerdev.travelstoragemanager.service.UpdaterErrorHandlerService;
import com.summerdev.travelstoragemanager.storage.ServiceTypeServiceStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.06.2022
 * Time: 21:40
 */
@Configuration
public class TaskServiceStorageInit {

    @Bean
    ServiceTypeServiceStorage<CursorService> cursorService(Set<CursorService> initServices) {
        return new ServiceTypeServiceStorage<>(initServices);
    }

    @Bean
    ServiceTypeServiceStorage<TravelInfoUpdaterService> travelInfoUpdaterService(Set<TravelInfoUpdaterService> initServices) {
        return new ServiceTypeServiceStorage<>(initServices);
    }

    @Bean
    ServiceTypeServiceStorage<UpdaterErrorHandlerService> updaterErrorHandlerService(Set<UpdaterErrorHandlerService> initServices) {
        return new ServiceTypeServiceStorage<>(initServices);
    }
}
