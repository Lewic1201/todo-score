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
 * @since 2020/1/16 23:27
 */
@Data
@Entity
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)
public class Task implements Serializable {

    private static final long serialVersionUID = 5415243201339744404L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Summary.class)
    private Integer id;

    @Column(name = "content")
    @JsonView(View.Summary.class)
    private String content;

    @Column(name = "score")
    @JsonView(View.Summary.class)
    private Integer score;

    @Column(name = "status")
    @JsonView(View.Summary.class)
    private Integer status;

    @Column(name = "description")
    @JsonView(View.Summary.class)
    private String description;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "cycle_type_id")
    @JsonView(View.Summary.class)
    private CycleType cycleType;

    @Column(name = "deleted")
    @JsonView(View.NoShow.class)
    private Boolean deleted;

    @CreatedDate
    @Column(name = "create_time")
    @JsonView(View.Detail.class)
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    @JsonView(View.Detail.class)
    private Date updateTime;

}
