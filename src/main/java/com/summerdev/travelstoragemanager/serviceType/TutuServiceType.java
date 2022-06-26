package com.summerdev.travelstoragemanager.serviceType;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 26.06.2022
 * Time: 23:50
 */
public interface TutuServiceType extends ServiceType {
    @Override
    default Class<? extends ServiceType> getServiceTypeClass() {
        return TutuServiceType.class;
    }
}
