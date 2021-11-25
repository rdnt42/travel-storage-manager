package com.summerdev.travelstoragemanager.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 31.05.2021
 * Time: 23:42
 */
@Entity(name = "hotels_info")
@DynamicInsert
@Data
public class HotelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private GeoNameData city;

    private Long stars;

    private Long cost;

//    @Column(name = "is_actual_data", columnDefinition = "boolean default false")
    private Boolean isActualData;
}
