package com.lewic.todoscore.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lewic
 * @since 2020/1/18 17:48
 */

@Data
@Entity
@Table(name = "workday")
public class Workday implements Serializable {

    private static final long serialVersionUID = -4180650072333484258L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @Column(name = "day")
    private Date day;

    @Column(name = "is_workday")
    private Boolean isWorkday;

    @Column(name = "remark")
    private String remark;

}
