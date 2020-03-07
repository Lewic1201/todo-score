package com.lewic.todoscore.vo;

import lombok.Data;

/**
 * @author lewic
 * @since 2020/3/7 19:51
 */
@Data
public class LoginVo {

    private String token;

    public LoginVo(String token) {
        this.token = token;
    }
}
