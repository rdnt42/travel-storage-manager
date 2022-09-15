package com.summerdev.travelstoragemanager.entity.directory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 15.09.2022
 * Time: 21:47
 */
@Setter
@Getter
@Table(name = "comfort_types")
@Entity
public class ComfortType implements Serializable {

    @Column(name = "comfort_type_id")
    @Id
    private Integer id;

    @Column(name = "comfort_type_name")
    private String comfortTypeName;
}
