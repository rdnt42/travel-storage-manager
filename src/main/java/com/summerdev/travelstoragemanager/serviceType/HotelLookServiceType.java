package com.summerdev.travelstoragemanager.serviceType;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 26.06.2022
 * Time: 22:31
 */
public interface HotelLookServiceType extends ServiceType{
    @Override
    default Class<? extends ServiceType> getServiceTypeClass() {
        return HotelLookServiceType.class;
    }
}
