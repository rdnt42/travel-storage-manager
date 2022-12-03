package com.summerdev.travelstoragemanager.entity.directory;

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
}
