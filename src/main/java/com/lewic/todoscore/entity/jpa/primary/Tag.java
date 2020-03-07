package com.lewic.todoscore.entity.jpa.primary;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lewic
 * @since 2020/3/3 22:02
 */

@Data
@Entity
@Table(name = "tag")
@EntityListeners(AuditingEntityListener.class)
public class Tag implements Serializable {

    private static final long serialVersionUID = -8430176620365775272L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Summary.class)
    private Integer id;

    @Column(name = "user")
    @JsonView(View.Summary.class)
    private String user;

    @Column(name = "name")
    @JsonView(View.Summary.class)
    private String name;

    @Column(name = "description")
    @JsonView(View.Summary.class)
    private String description;

    @CreatedDate
    @Column(name = "create_time")
    @JsonView(View.Detail.class)
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    @JsonView(View.Detail.class)
    private Date updateTime;

}
