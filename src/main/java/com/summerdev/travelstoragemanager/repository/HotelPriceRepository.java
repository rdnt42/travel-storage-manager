package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.hotel.HotelPrice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 27.11.2021
 * Time: 23:48
 */
public interface HotelPriceRepository extends JpaRepository<HotelPrice, Long> {
}
