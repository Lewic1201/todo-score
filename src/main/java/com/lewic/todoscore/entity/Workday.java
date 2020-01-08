package com.lewic.todoscore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author lewic
 * @apiNote 特殊工作日
 * @since 2020/1/7 23:12
 */
@Data
@ApiModel
public class Workday implements Serializable {

    @ApiModelProperty(value = "编号", name = "id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "节日类型", name = "holidayType", example = "1")
    private Integer holidayType;

    @ApiModelProperty(value = "特殊日期", name = "specialDay", example = "2020-01-01")
    private Date specialDay;

    @ApiModelProperty(value = "是否为工作日", name = "isWorkday", example = "test")
    private Boolean isWorkday;

    @ApiModelProperty(value = "备注", name = "remark", example = "test")
    private String remark;

}
