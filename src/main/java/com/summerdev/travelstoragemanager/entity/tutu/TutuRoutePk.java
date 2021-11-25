package com.summerdev.travelstoragemanager.entity.tutu;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 14:22
 */
@Embeddable
@Data
public class TutuRoutePk implements Serializable {

    @Column(name = "departure_station_id")
    private Long departureStationId;

    @Column(name = "arrival_station_id")
    private Long arrivalStationId;
}
