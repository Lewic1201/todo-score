package com.lewic.todoscore.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
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
 * @since 2020/3/14 15:25
 */

@Data
@Entity
@Table(name = "access_trace")
@EntityListeners(AuditingEntityListener.class)
public class AccessTrace implements Serializable {

    private static final long serialVersionUID = 5826398025302092828L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "req_method")
    private String reqMethod;

    @Column(name = "req_path")
    private String reqPath;

    @Column(name = "req_ip")
    private String reqIp;

    @Column(name = "user_id")
    private Integer userId;

    // todo 后期考虑全局都用userId,删除username
    @Column(name = "username")
    private String username;

    @CreatedDate
    @Column(name = "create_time")
    @JsonView(View.Detail.class)
    private Date createTime;

}
