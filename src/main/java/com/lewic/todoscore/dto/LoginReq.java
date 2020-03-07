package com.lewic.todoscore.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author lewic
 * @since 2020/3/7 22:00
 */
@ApiModel
@Data
public class LoginReq {

    private String username;

    private String password;

}


