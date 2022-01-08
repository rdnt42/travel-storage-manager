package com.summerdev.travelstoragemanager.entity.tutu;

import com.summerdev.travelstoragemanager.entity.SeatType;
import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA
 * User: alovyannikov
 * Date: 25.12.2021
 * Time: 13:56
 */
@Data
@Entity(name = "train_prices")
public class TrainPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_price_id")
    private Long id;

    @Column(name = "train_info_id")
    private Long trainInfoId;

    @ManyToOne
    @JoinColumn(name = "train_info_id", insertable = false, updatable = false)
    private TrainInfo trainInfo;

    private Double cost;

    @Column(name = "comfort_type_id")
    @Enumerated(EnumType.STRING)
    private ComfortType comfortType;

//    @Column(name = "seat_type_id")
//    private Long seatTypeId = 0L;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "seat_type_id", insertable = false, updatable = false)
//    private SeatType seatType;

    public TrainPrice() {

    }

    public TrainPrice(Double cost, ComfortType comfortType) {
        this.cost = cost;
        this.comfortType = comfortType;
    }
}
