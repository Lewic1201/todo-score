package com.lewic.todoscore.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author lewic
 * @since 2020/3/7 20:14
 */
@ApiModel
@Data
public class RegisterReq {

    private String username;

    private String password;

    private String mobile;

    private Integer sex;

    private Date birthday;

    private String address;

    private String email;
}
