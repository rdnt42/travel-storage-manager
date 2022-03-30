package com.summerdev.travelstoragemanager.entity.train;

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
 * Date: 30.05.2021
 * Time: 19:01
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
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
