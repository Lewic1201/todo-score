package com.lewic.todoscore.entity;

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
 * @since 2020/3/3 22:06
 */
@Data
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    private static final long serialVersionUID = 1810054537374059509L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Summary.class)
    private Integer id;

    @Column(name = "username")
    @JsonView(View.Summary.class)
    private String username;

    @Column(name = "password")
    @JsonView(View.Summary.class)
    private String password;

    @Column(name = "role")
    @JsonView(View.Summary.class)
    private Integer role;

    @Column(name = "status")
    @JsonView(View.NoShow.class)
    private Integer status;

    @CreatedDate
    @Column(name = "create_time")
    @JsonView(View.NoShow.class)
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    @JsonView(View.NoShow.class)
    private Date updateTime;

    @Column(name = "delete_time")
    @JsonView(View.NoShow.class)
    private Date deleteTime;
}
