package com.summerdev.travelstoragemanager.serviceType;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 26.06.2022
 * Time: 22:35
 */
public interface ServiceType {
    Class<? extends ServiceType> getServiceTypeClass();
}
