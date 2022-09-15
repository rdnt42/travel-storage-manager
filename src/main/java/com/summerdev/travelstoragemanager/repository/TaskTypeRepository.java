package com.summerdev.travelstoragemanager.repository;

import com.summerdev.travelstoragemanager.entity.directory.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 23:56
 */
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {

}
