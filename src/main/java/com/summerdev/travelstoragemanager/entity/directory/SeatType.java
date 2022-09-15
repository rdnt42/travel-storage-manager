package com.summerdev.travelstoragemanager.entity.directory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 30.05.2021
 * Time: 19:24
 */
@Getter
@Setter
@Table(name = "seat_types")
@Entity
public class SeatType implements Serializable {
    @Id
    @Column(name = "seat_type_id")
    private Long id;

    private String seatTypeName;

    @Getter
    public enum SeatTypeEnum {
        SEAT_TYPE_ID_ECONOMY(1L, "Плацкарт", "plazcard"),
        SEAT_TYPE_ID_COUPE(2L, "Купе", "coupe"),
        SEAT_TYPE_ID_SEDENTARY(3L, "Сидячий", "sedentary"),
        SEAT_TYPE_ID_LUX(4L, "Люкс", "lux"),
        SEAT_TYPE_ID_SOFT(5L, "Мягкий", "soft");

        private final Long id;
        private final String name;
        private final String dsc;

        SeatTypeEnum(Long id, String name, String dsc) {
            this.id = id;
            this.name = name;
            this.dsc = dsc;
        }

        public static SeatTypeEnum getByDsc(String text) {
            for (SeatTypeEnum seatType : SeatTypeEnum.values()) {
                if(seatType.getDsc().equalsIgnoreCase(text)) {
                    return seatType;
                }
            }

            return null;
        }
    }

}
