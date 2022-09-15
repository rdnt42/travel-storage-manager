package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.directory.ComfortType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 15.09.2022
 * Time: 22:30
 */
public interface ComfortTypeRepository extends JpaRepository<ComfortType, Integer> {
}
