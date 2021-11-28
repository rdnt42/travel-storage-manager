package com.summerdev.travelstoragemanager.entity.tutu;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tutu_stations")
@Data
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
