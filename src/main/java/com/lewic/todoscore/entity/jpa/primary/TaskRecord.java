package com.lewic.todoscore.entity.jpa.primary;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@EntityListeners(AuditingEntityListener.class)
public class TaskRecord implements Serializable {

    private static final long serialVersionUID = 557832937289615039L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Summary.class)
    private Integer id;

//    @JsonView(View.Summary.class)
//    @Column(name = "task_id")
//    private Integer taskId;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "task_id")
    @JsonView(View.Summary.class)
    private Task task;

    @Column(name = "content")
    @JsonView(View.Summary.class)
    private String content;

    @Column(name = "description")
    @JsonView(View.Summary.class)
    private String description;

    @Column(name = "score_value")
    @JsonView(View.Summary.class)
    private Integer scoreValue;

    @Column(name = "finish")
    @JsonView(View.Summary.class)
    private Boolean finish;

    // todo 该参数只能通过后台修改
    @Column(name = "score")
    @JsonView(View.Summary.class)
    private Integer score;

    @CreatedDate
    @Column(name = "create_time")
    @JsonView(View.Detail.class)
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    @JsonView(View.Detail.class)
    private Date updateTime;
}
