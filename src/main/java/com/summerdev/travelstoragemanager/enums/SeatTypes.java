package com.summerdev.travelstoragemanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 03.12.2022
 * Time: 20:22
 */
@AllArgsConstructor
@Getter
public enum SeatTypes {
    SEAT_TYPE_ID_ECONOMY(1L, "Плацкарт", "plazcard"),
    SEAT_TYPE_ID_COUPE(2L, "Купе", "coupe"),
    SEAT_TYPE_ID_SEDENTARY(3L, "Сидячий", "sedentary"),
    SEAT_TYPE_ID_LUX(4L, "Люкс", "lux"),
    SEAT_TYPE_ID_SOFT(5L, "Мягкий", "soft");

    private final Long id;
    private final String name;
    private final String dsc;

    public static SeatTypes getByDsc(String text) {
        for (SeatTypes seatType : SeatTypes.values()) {
            if (seatType.getDsc().equalsIgnoreCase(text)) {
                return seatType;
            }
        }

        return null;
    }
}
