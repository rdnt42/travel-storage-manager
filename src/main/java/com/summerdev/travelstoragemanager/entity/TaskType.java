package com.summerdev.travelstoragemanager.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 22:58
 */
@Entity(name = "task_types")
@Data
public class TaskType {
    @Id
    @Column(name = "task_type_id")
    private Long id;

    private String taskTypeName;

    public enum TaskTypes {
        TASK_GET_HOTELS_INFO(1L),
        TASK_GET_TRAINS_INFO(2L),
        TASK_GET_PLAINS_INFO(3L);

        private final Long idValue;

        TaskTypes(Long idValue) {
            this.idValue = idValue;
        }

        public Long getIdValue() {
            return idValue;
        }

        public boolean equalsByValue(Long value) {
            return value.equals(this.idValue);
        }
    }
}
