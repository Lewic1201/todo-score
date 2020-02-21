package com.lewic.todoscore.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author lewic
 * @since 2020/2/14 11:27
 */
@Data
public class TaskDto implements Serializable {

    private static final long serialVersionUID = 5415243201119744404L;

    private Integer id;

    private String content;

    private Integer score;

    private Integer status;

    private String description;

    private Integer cycleTypeId;
    
}
