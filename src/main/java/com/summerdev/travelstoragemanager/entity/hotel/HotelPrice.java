package com.summerdev.travelstoragemanager.entity.hotel;

import com.summerdev.travelstoragemanager.enums.ComfortTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    @Column(name = "comfort_type")
    @Enumerated(EnumType.STRING)
    private ComfortTypes comfortTypes;

    public HotelPrice() {
    }

    public HotelPrice(HotelInfo hotelInfo, Double cost, ComfortTypes comfortTypes) {
        this.hotelInfo = hotelInfo;
        this.cost = cost;
        this.comfortTypes = comfortTypes;
    }
}
