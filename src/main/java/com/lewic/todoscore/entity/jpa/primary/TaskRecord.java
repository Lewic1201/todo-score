package com.lewic.todoscore.entity.jpa.primary;

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
 * @since 2020/1/18 23:58
 */
@Data
@Entity
@Table(name = "task_record")
public class TaskRecord implements Serializable {

    private static final long serialVersionUID = 557832937289615039L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "finish")
    private Boolean finish;

    // todo 可以关联task去掉此参数
    @Column(name = "score")
    private Integer score;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "updateTime")
    private Date updateTime;
}
