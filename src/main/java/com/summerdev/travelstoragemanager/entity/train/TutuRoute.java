package com.summerdev.travelstoragemanager.entity.train;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "tutu_routes")
@Entity
public class TutuRoute implements Serializable {
    @Id
    @Column(name = "tutu_route_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departure_station_id", insertable = false, updatable = false)
    private TutuStation departureStation;

    @ManyToOne
    @JoinColumn(name = "arrival_station_id", insertable = false, updatable = false)
    private TutuStation arrivalStation;
}
