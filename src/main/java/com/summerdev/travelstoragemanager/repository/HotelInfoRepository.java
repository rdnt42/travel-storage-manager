package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.HotelInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 0:17
 */
public interface HotelInfoRepository extends JpaRepository<HotelInfo, Long> {
}
