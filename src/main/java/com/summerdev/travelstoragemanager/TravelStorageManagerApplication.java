package com.summerdev.travelstoragemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TravelStorageManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelStorageManagerApplication.class, args);
    }

}
