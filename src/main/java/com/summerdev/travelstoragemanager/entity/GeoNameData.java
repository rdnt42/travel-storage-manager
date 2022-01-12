package com.summerdev.travelstoragemanager.entity;

import lombok.Data;
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
 * Date: 28.05.2021
 * Time: 23:26
 */

@Getter
@Setter
@Table(name = "geo_names")
@Entity
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
