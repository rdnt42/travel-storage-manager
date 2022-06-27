package com.summerdev.travelstoragemanager.storage;

import com.summerdev.travelstoragemanager.serviceType.ServiceType;
import org.springframework.aop.support.AopUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 26.06.2022
 * Time: 21:47
 */
public class ServiceTypeServiceStorage<T> {
    private final Map<Class<? extends ServiceType>, T> initServicesMap = new HashMap<>();

    public ServiceTypeServiceStorage(Set<T> initServices) {
        initServiceMap(initServices);
    }

    private void initServiceMap(Set<T> services) {
        for (T service : services) {
            Class<?> targetClass = AopUtils.getTargetClass(service);

            for (Class<?> targetInterface : targetClass.getInterfaces()) {
                if (ServiceType.class.isAssignableFrom(targetInterface)) {

                    @SuppressWarnings("unchecked")
                    Class<? extends ServiceType> keyClass = (Class<? extends ServiceType>) targetInterface;
                    throwIfValueExists(keyClass);
                    initServicesMap.put(keyClass, service);
                }
            }

        }
    }

    private void throwIfValueExists(Class<? extends ServiceType> keyClass) {
        initServicesMap.computeIfPresent(keyClass, (key, value) -> {
            throw new IllegalArgumentException("Value for key " + key + " already exists. Value: " + value);
        });
    }
}
