package com.summerdev.travelstoragemanager.storage;

import com.summerdev.travelstoragemanager.serviceType.ServiceType;
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

    public TaskServiceStorageImpl(Set<ServiceType> serviceTypes) {
        initServiceTypeStorageMap(serviceTypes);
    }

    private void initServiceTypeStorageMap(Set<ServiceType> serviceTypes) {
        for (ServiceType serviceType : serviceTypes) {
            Class<? extends ServiceType> serviceTypeClass = getServiceTypeClass(serviceType);

            serviceTypeStorageMap.putIfAbsent(serviceTypeClass, new HashSet<>());
            serviceTypeStorageMap.get(serviceTypeClass).add(serviceType);
        }
    }

    private Class<? extends ServiceType> getServiceTypeClass(ServiceType serviceType) {
        return serviceType.getServiceTypeClass();
    }
}
