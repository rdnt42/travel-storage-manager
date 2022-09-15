package com.summerdev.travelstoragemanager.entity.train;

import com.summerdev.travelstoragemanager.entity.directory.SeatType;
import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * User: marowak
 * Date: 25.12.2021
 * Time: 13:56
 */
@Getter
@Setter
@Entity(name = "train_prices")
public class TrainPrice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_price_id")
    private Long id;

    @Column(name = "train_info_id", insertable = false, updatable = false)
    private Long trainInfoId;

    @ManyToOne
    @JoinColumn(name = "train_info_id")
    private TrainInfo trainInfo;

    private Double cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comfort_type_id")
    private ComfortType comfortType;

    @Column(name = "seat_type_id")
    private Long seatTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_type_id", insertable = false, updatable = false)
    private SeatType seatType;

    public TrainPrice() {

    }

    public TrainPrice(Double cost, ComfortType comfortType, Long seatTypeId) {
        this.cost = cost;
        this.comfortType = comfortType;
        this.seatTypeId = seatTypeId;
    }
}
