package com.lewic.todoscore.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import lombok.Data;
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
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -7995074991254041815L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Summary.class)
    private Integer id;

    @Column(name = "user_id")
    @JsonView(View.Summary.class)
    private String userId;

    @Column(name = "nickname")
    @JsonView(View.Summary.class)
    private String nickname;

    @Column(name = "sex")
    @JsonView(View.Summary.class)
    private String sex;

    @Column(name = "head_portrait")
    @JsonView(View.Summary.class)
    private String headPortrait;

    @Column(name = "signature")
    @JsonView(View.Summary.class)
    private String signature;

    @Column(name = "birthday")
    @JsonView(View.Summary.class)
    private Date birthday;

    @Column(name = "address")
    @JsonView(View.Summary.class)
    private String address;

    @Column(name = "email")
    @JsonView(View.Summary.class)
    private String email;

    @Column(name = "mobile")
    @JsonView(View.Summary.class)
    private String mobile;

    @LastModifiedDate
    @Column(name = "update_time")
    @JsonView(View.Detail.class)
    private Date updateTime;
}

