package com.summerdev.travelstoragemanager.entity;

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


    public enum SeatTypes {
        SEAT_TYPE_ID_COMMON,
        SEAT_TYPE_ID_ECONOMY,
        SEAT_TYPE_ID_COUPE,
        SEAT_TYPE_ID_SEDENTARY,
        SEAT_TYPE_ID_LUX,
        SEAT_TYPE_ID_SOFT
    }

}
