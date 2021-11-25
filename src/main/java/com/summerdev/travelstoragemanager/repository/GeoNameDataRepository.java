package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.GeoNameData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 25.11.2021
 * Time: 23:14
 */
public interface GeoNameDataRepository extends JpaRepository<GeoNameData, Long> {
    Optional<GeoNameData> findFirstByGeoNameRu(String geoNameRu);
}
