package com.summerdev.travelstoragemanager.entity.tutu;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import com.summerdev.travelstoragemanager.entity.SeatType;
import com.summerdev.travelstoragemanager.entity.hotel.HotelPrice;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alovyannikov
 * Date: 30.05.2021
 * Time: 19:01
 */
@Entity(name = "trains_info")
@Data
public class TrainInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_info_id")
    private Long id;

    private String trainNumber;

    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private GeoNameData departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private GeoNameData arrivalCity;

    private Long travelTime;

    private Date lastUpdate;

    @OneToMany(mappedBy = "trainInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainPrice> trainPrices;

    public void addNewPrices(List<TrainPrice> prices) {
        if (trainPrices == null) {
            trainPrices = prices;
        } else {
            trainPrices.clear();
            trainPrices.addAll(prices);
        }
    }

}
