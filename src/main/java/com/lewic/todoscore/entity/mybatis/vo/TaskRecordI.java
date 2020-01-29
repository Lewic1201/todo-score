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
    @ApiModelProperty(value = "是否完成", name = "status", example = "1")
    private Boolean finish;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "得分", name = "cycleType", example = "1")
    private Integer score;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "创建时间", name = "createTime", example = "allowNull")
    private Date createTime;

    @JsonView(View.Detail.class)
    @ApiModelProperty(value = "更新时间", name = "updateTime", example = "allowNull")
    private Date updateTime;

}
