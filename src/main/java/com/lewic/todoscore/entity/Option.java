package com.lewic.todoscore.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lewic
 * @apiNote 主题任务
 * @since 2019/6/14 15:38
 **/
@Data
@ApiModel(description = "任务选项")
public class Option implements Serializable {

    private static final long serialVersionUID = 4359709213241100087L;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "编号", name = "id", example = "1")
    private String id;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "题目内容", name = "content", example = "是")
    private String content;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "分数", name = "score", example = "10")
    private Integer score;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "循环周期", name = "subjectId", example = "1")
    private Integer subjectId;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "状态", name = "status", example = "1")
    private Integer status;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "详情描述", name = "description", example = "test")
    private String description;

    // TODO 任务下次出现时间

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "allowNull")
    private String createTime;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "更新时间", name = "updateTime", example = "allowNull")
    private String updateTime;


    public Option() {
    }

    public Option(String content, Integer score, Integer subjectId, Integer status, String description, String createTime, String updateTime) {
        this.content = content;
        this.score = score;
        this.subjectId = subjectId;
        this.status = status;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;

    }

    public Option(String id, String content, Integer score, Integer subjectId, Integer status, String description, String createTime, String updateTime) {
        this.id = id;
        this.content = content;
        this.score = score;
        this.subjectId = subjectId;
        this.status = status;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;

    }
}
