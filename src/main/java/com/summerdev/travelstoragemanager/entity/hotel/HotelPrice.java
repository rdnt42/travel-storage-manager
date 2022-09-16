package com.summerdev.travelstoragemanager.entity.hotel;

import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.11.2021
 * Time: 20:24
 */
@Getter
@Setter
@Table(name = "hotel_prices")
@Entity
public class HotelPrice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_price_id")
    private Long id;

    @Column(name = "hotel_info_id", insertable = false, updatable = false)
    private Long hotelInfoId;

    @ManyToOne
    @JoinColumn(name = "hotel_info_id")
    private HotelInfo hotelInfo;

    private Double cost;

    @ManyToOne
    @JoinColumn(name = "comfort_type_id")
    private ComfortType comfortType;

    public HotelPrice() {
    }

    public HotelPrice(HotelInfo hotelInfo, Double cost, ComfortType comfortType) {
        this.hotelInfo = hotelInfo;
        this.cost = cost;
        this.comfortType = comfortType;
    }
}
