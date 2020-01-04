package com.lewic.todoscore.constant;

import lombok.Getter;

/**
 * @author lewic
 * @since 2020/1/4 23:41
 */

public class Enums {

    /**
     * 角色类型
     */
    @Getter
    public enum RoleType {

        ADMIN(0, "admin_user"),

        COMMON(1, "common_user");

        private Integer code;

        private String value;

        RoleType(Integer code, String value) {
            this.code = code;
            this.value = value;
        }
    }


    /**
     * 账户状态
     */
    @Getter
    public enum AccountStatus {

        NORMAL(0, "normal"),

        FREEZE(1, "freeze"),

        DELETE(2, "delete");

        private Integer code;

        private String value;

        AccountStatus(Integer code, String value) {
            this.code = code;
            this.value = value;
        }
    }

}
