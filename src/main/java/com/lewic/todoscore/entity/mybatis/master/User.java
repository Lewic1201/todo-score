package com.lewic.todoscore.entity.mybatis.master;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lewic
 * @apiNote 账号
 * @since 2019/6/14 15:38
 **/
@Data
@ApiModel(description = "账户")
public class User implements Serializable {

    private static final long serialVersionUID = 4359709211352400087L;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "编号", name = "id", example = "1")
    private Integer id;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "用户名", name = "ruleName", example = "test")
    private String username;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "密码", name = "password", example = "test")
    private String password;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "角色", name = "role", example = "test")
    private Integer role;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "手机号", name = "mobile", example = "test")
    private String mobile;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "账号状态", name = "status", example = "test")
    private Integer status;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "test")
    private String createTime;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "更新时间", name = "updateTime", example = "test")
    private String updateTime;

}
