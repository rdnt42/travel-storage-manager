package com.summerdev.travelstoragemanager.entity.hotel;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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

@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date lastUpdate;

    @Builder.Default
    @OneToMany(mappedBy = "hotelInfo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HotelPrice> hotelPrices = new ArrayList<>();

    public void addPrice(HotelPrice price) {
        hotelPrices.add(price);
    }

    public void addNewPrices(List<HotelPrice> prices) {
        hotelPrices.clear();
        hotelPrices.addAll(prices);
    }
}
