package com.summerdev.travelstoragemanager.entity.train;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "tutu_stations")
@Entity
public class TutuStation implements Serializable {
    @Id
    @Column(name = "station_id")
    private Long id;

    private String stationName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "geo_name_id")
    private GeoNameData geoName;

    @Column(name = "geo_name_id", insertable = false, updatable = false)
    private Long geoNameId;
}
