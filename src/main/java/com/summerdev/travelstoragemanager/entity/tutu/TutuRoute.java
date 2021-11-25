package com.summerdev.travelstoragemanager.entity.tutu;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tutu_routes")
@Data
public class TutuRoute implements Serializable {
    @EmbeddedId
    private TutuRoutePk id;

    @ManyToOne
    @JoinColumn(name = "departure_station_id", insertable = false, updatable = false)
    private TutuStation departureStation;

    @ManyToOne
    @JoinColumn(name = "arrival_station_id", insertable = false, updatable = false)
    private TutuStation arrivalStation;

}
