package com.summerdev.travelstoragemanager.entity.hotel;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 31.05.2021
 * Time: 23:42
 */
@Getter
@Setter
@Table(name = "hotels_info")
@Entity
public class HotelInfo implements Serializable {
    @Id
    @Column(name = "hotel_info_id")
    private Long id;

    private String hotelName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private GeoNameData city;

    private Long stars;

    private Date lastUpdate;

    @OneToMany(mappedBy = "hotelInfo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HotelPrice> hotelPrices;

    public void addPrice(HotelPrice price) {
        if (hotelPrices == null) {
            hotelPrices = new ArrayList<>();
        }

        hotelPrices.add(price);
    }

    public void addNewPrices(List<HotelPrice> prices) {
        if (hotelPrices == null) {
            hotelPrices = prices;
        } else {
            hotelPrices.clear();
            hotelPrices.addAll(prices);
        }
    }
}
