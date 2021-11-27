package com.summerdev.travelstoragemanager.entity.hotel;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Column(name = "hotel_info_id")
    private Long id;

    private String hotelName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private GeoNameData city;

    private Long stars;

    private Date lastUpdate;

    @OneToMany(mappedBy = "hotelInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelPrice> hotelPrices = new ArrayList<>();

    public void addPrice(HotelPrice price) {
        hotelPrices.add(price);
    }
}
