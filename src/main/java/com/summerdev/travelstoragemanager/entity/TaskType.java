package com.summerdev.travelstoragemanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 22:58
 */
@Getter
@Setter
@Table(name = "task_types")
@Entity
public class TaskType {
    @Id
    @Column(name = "task_type_id")
    private Long id;

    private String taskTypeName;

    @Getter
    public enum TaskTypeEnum {
        TASK_GET_HOTELS_INFO(1L),
        TASK_GET_TRAINS_INFO(2L),
        TASK_GET_PLAINS_INFO(3L);

        private final Long idValue;

        TaskTypeEnum(Long idValue) {
            this.idValue = idValue;
        }

        public static TaskTypeEnum getById(Long id) {
            for (TaskTypeEnum taskTypeEnum : TaskTypeEnum.values()) {
                if(taskTypeEnum.idValue.equals(id)) {
                    return taskTypeEnum;
                }
            }

            throw new NullPointerException("TaskType with id: " + id + " does not exist");
        }
    }
}
