package com.summerdev.travelstoragemanager.entity.train;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * Date: 30.05.2021
 * Time: 19:01
 */
@Getter
@Setter
@Table(name = "trains_info")
@Entity
public class TrainInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_info_id")
    private Long id;

    private String trainNumber;

    @Column(name = "departure_city_id", insertable = false, updatable = false)
    private Long departureCityId;

    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private GeoNameData departureCity;

    @Column(name = "arrival_city_id", insertable = false, updatable = false)
    private Long arrivalCityId;

    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private GeoNameData arrivalCity;

    private Long travelTime;

    private Date lastUpdate;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "trainInfo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TrainPrice> trainPrices = new ArrayList<>();

    public void addPrice(TrainPrice price) {
        trainPrices.add(price);
        price.setTrainInfo(this);
    }

    public void addNewPrices(List<TrainPrice> prices) {
        trainPrices.clear();
        for (TrainPrice price : prices) {
            addPrice(price);
        }
    }

}
