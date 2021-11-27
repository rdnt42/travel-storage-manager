package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.hotel.HotelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 24.11.2021
 * Time: 0:17
 */
public interface HotelInfoRepository extends JpaRepository<HotelInfo, Long> {
    HotelInfo findFirstByOrderByIdAsc();

    void deleteByIdIn(List<Long> ids);

    @Modifying
    @Query("delete from hotels_info hi where hi.id in ?1")
    void deleteHotelsInfoWithIds(List<Long> ids);
}
