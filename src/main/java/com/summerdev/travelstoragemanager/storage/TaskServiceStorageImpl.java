package com.summerdev.travelstoragemanager.storage;

import com.summerdev.travelstoragemanager.service.TravelInfoUpdaterService;
import com.summerdev.travelstoragemanager.serviceType.ServiceType;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 26.06.2022
 * Time: 21:47
 */
@Service
public class TaskServiceStorageImpl implements TaskServiceStorage {
    private final Map<Class<? extends ServiceType>, Set<ServiceType>> serviceTypeStorageMap = new HashMap<>();
    private final Map<Class<? extends ServiceType>, TravelInfoUpdaterService> updaterServiceMap = new HashMap<>();

    public TaskServiceStorageImpl(Set<ServiceType> serviceTypes) {
        initServiceTypeStorageMap(serviceTypes);
        for (Map.Entry<Class<? extends ServiceType>, Set<ServiceType>> entry : serviceTypeStorageMap.entrySet()) {
            initUpdaterServiceMap(entry.getKey(), entry.getValue());
        }
    }

    private void initServiceTypeStorageMap(Set<ServiceType> serviceTypes) {
        for (ServiceType serviceType : serviceTypes) {
            Class<? extends ServiceType> serviceTypeClass = getServiceTypeClass(serviceType);

            serviceTypeStorageMap.putIfAbsent(serviceTypeClass, new HashSet<>());
            serviceTypeStorageMap.get(serviceTypeClass).add(serviceType);
        }
    }

    private void initUpdaterServiceMap(Class<? extends ServiceType> serviceTypeClass, Set<ServiceType> serviceTypes) {
        TravelInfoUpdaterService service;
        for (ServiceType serviceType : serviceTypes) {
            if ((service = getUpdaterServiceMap(serviceType)) != null) {
                updaterServiceMap.put(serviceTypeClass, service);
                return;
            }
        }
        throw new IllegalArgumentException("TravelInfoUpdaterService implementation not found for service" +
                serviceTypeClass);
    }

    private TravelInfoUpdaterService getUpdaterServiceMap(ServiceType serviceType) {
        Class<?> targetClass = AopUtils.getTargetClass(serviceType);
        for (Class<?> targetInterface : targetClass.getInterfaces()) {
            if (TravelInfoUpdaterService.class.isAssignableFrom(targetInterface)) {
                return (TravelInfoUpdaterService) serviceType;
            }
        }

        return null;
    }

    private Class<? extends ServiceType> getServiceTypeClass(ServiceType serviceType) {
        return serviceType.getServiceTypeClass();
    }
}
