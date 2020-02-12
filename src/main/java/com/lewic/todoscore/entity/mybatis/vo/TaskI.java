package com.lewic.todoscore.entity.mybatis.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lewic
 * @apiNote 任务
 * @since 2019/6/14 15:38
 **/
@Data
@ApiModel(description = "任务选项")
public class TaskI implements Serializable {

    private static final long serialVersionUID = 4359709213241100087L;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "编号", name = "id", example = "1")
    private Integer id;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "内容", name = "content", example = "是")
    private String content;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "分数", name = "score", example = "10")
    private Integer score;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "状态", name = "status", example = "1")
    private Integer status;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "描述", name = "description", example = "test")
    private String description;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "循环方式编号", name = "cycleTypeId", example = "1")
    private Integer cycleTypeId;

    @JsonView(View.NoShow.class)
    @ApiModelProperty(value = "是否删除", name = "deleted", example = "false")
    private Boolean deleted;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "allowNull")
    private String createTime;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "更新时间", name = "updateTime", example = "allowNull")
    private String updateTime;

}
