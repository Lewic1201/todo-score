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
 * @since 2020/1/18 17:41
 */

@Data
@Entity
@Table(name = "cycle_type")
public class CycleType implements Serializable {

    private static final long serialVersionUID = -6961502514950128221L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private Date startTime;

    //todo 次数应该加在任务上面
    @Column(name = "times")
    private Integer times;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "workday_status")
    private Integer workdayStatus;

    @Column(name = "cron_expression")
    private String cronExpression;

}
