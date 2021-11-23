package com.summerdev.travelstoragemanager.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 19:01
 */
@Entity(name = "trains_info")
@Data
public class TrainInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_info_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private GeoNameData departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private GeoNameData arrivalCity;

    private Long travelTime;

    @Column(name = "seat_type_id")
    private Long seatTypeId = 0L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_type_id", insertable = false, updatable = false)
    private SeatType seatType;

    private Long cost;

}
