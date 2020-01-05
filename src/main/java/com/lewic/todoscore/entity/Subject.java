package com.lewic.todoscore.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author lewic
 * @apiNote 账号
 * @since 2019/6/14 15:38
 **/
@Data
@ApiModel(description = "任务主题")
public class Subject implements Serializable {

    private static final long serialVersionUID = 4359709211352401087L;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "编号", name = "id", example = "1")
    private Integer id;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "内容", name = "content", example = "test")
    private String content;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "状态", name = "status", example = "1")
    private Integer status;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "循环类型", name = "cycleType", example = "1")
    private Integer cycleType;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "父ID", name = "superId", example = "allowNull")
    private Integer superId;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "描述", name = "description", example = "test")
    private String description;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "过期时间", name = "expirationTime", example = "allowNull")
    private String expirationTime;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "allowNull")
    private String createTime;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "更新时间", name = "updateTime", example = "allowNull")
    private String updateTime;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "删除时间", name = "deleteTime", example = "allowNull")
    private String deleteTime;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "选项列表")
    private List<Option> options;
}
