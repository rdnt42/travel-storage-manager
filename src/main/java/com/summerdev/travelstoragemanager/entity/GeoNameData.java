package com.summerdev.travelstoragemanager.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 28.05.2021
 * Time: 23:26
 */
@Entity(name = "geo_names")
@Data
public class GeoNameData implements Serializable {
    @Id
    @Column(name = "geo_name_id")
    private Long id;

    private String geoName;

    private String geoNameRu;

    private Float latitude;

    private Float longitude;

    private String countryCode;

    private String timezone;

}
