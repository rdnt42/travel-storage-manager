package com.summerdev.travelstoragemanager.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 22:51
 */
@Entity(name = "info_tasks")
@Data
public class InfoTask {
    @Id
    @Column(name = "info_tasks_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cursorId;

    @Column(insertable = false, updatable = false)
    private Long taskTypeId;

    @ManyToOne
    @JoinColumn(name = "task_type_id")
    private TaskType taskType;
}
