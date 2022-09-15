package com.summerdev.travelstoragemanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.11.2021
 * Time: 21:06
 */
@Getter
@AllArgsConstructor
public enum ComfortTypes {
    COMFORT_TYPE_CHEAP(1),
    COMFORT_TYPE_COMFORT(2),
    COMFORT_TYPE_LUXURY(3);

    private final int id;
}
