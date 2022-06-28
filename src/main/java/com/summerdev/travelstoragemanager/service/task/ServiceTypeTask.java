package com.summerdev.travelstoragemanager.service.task;

import com.summerdev.travelstoragemanager.serviceType.ServiceType;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 28.06.2022
 * Time: 22:47
 */
public interface ServiceTypeTask {
    Class<? extends ServiceType> getServiceTypeClass();
}
