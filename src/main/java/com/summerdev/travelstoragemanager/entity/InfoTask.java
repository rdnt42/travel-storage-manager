package com.summerdev.travelstoragemanager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 23.11.2021
 * Time: 22:51
 */
@Getter
@Setter
@Table(name = "info_tasks")
@Entity
public class InfoTask {
    @Id
    @Column(name = "info_tasks_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cursorId;

    @Column(name = "task_type_id", insertable = false, updatable = false)
    private Long taskTypeId;

    @ManyToOne
    @JoinColumn(name = "task_type_id")
    private TaskType taskType;
}
