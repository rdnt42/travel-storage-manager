package com.summerdev.travelstoragemanager.entity.hotel;

import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.11.2021
 * Time: 20:24
 */
@Entity(name = "hotel_prices")
@Data
public class HotelPrice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_price_id")
    private Long id;

    @Column(name = "hotel_info_id")
    private Long hotelInfoId;

    @ManyToOne
    @JoinColumn(name = "hotel_info_id", insertable = false, updatable = false)
    private HotelInfo hotelInfo;

    private Double cost;

    @Column(name = "comfort_type_id")
    @Enumerated(EnumType.STRING)
    private ComfortType comfortType;

    public HotelPrice() {
    }

    public HotelPrice(Long hotelInfoId, Double cost, ComfortType comfortType) {
        this.hotelInfoId = hotelInfoId;
        this.cost = cost;
        this.comfortType = comfortType;
    }
}
