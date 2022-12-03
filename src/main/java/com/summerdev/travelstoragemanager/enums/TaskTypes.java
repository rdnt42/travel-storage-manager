package com.summerdev.travelstoragemanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 03.12.2022
 * Time: 20:22
 */
@AllArgsConstructor
@Getter
public enum TaskTypes {
    TASK_GET_HOTELS_INFO(1L),
    TASK_GET_TRAINS_INFO(2L),
    TASK_GET_PLAINS_INFO(3L);

    private final Long idValue;

    public static TaskTypes getById(Long id) {
        for (TaskTypes taskTypeEnum : TaskTypes.values()) {
            if (taskTypeEnum.idValue.equals(id)) {
                return taskTypeEnum;
            }
        }

        throw new NullPointerException("TaskType with id: " + id + " does not exist");
    }
}
