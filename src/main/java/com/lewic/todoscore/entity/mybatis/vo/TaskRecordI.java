package com.lewic.todoscore.entity.mybatis.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lewic
 * @apiNote 账号
 * @since 2019/6/14 15:38
 **/
@Data
@ApiModel(description = "任务记录")
public class TaskRecordI implements Serializable {

    private static final long serialVersionUID = 4359709211352401087L;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "编号", name = "id", example = "1")
    private Integer id;

    @JsonView(View.Summary.class)
    private String taskId;

    @JsonView(View.Summary.class)
    private TaskI task;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "清单内容", name = "content", example = "1")
    private String content;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "清单描述", name = "description", example = "1")
    private String description;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "分值", name = "scoreValue", example = "10")
    private Integer scoreValue;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "是否完成", name = "finish", example = "0")
    private Boolean finish;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "得分", name = "score", example = "0")
    private Integer score;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "allowNull")
    private Date createTime;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "更新时间", name = "updateTime", example = "allowNull")
    private Date updateTime;

}
