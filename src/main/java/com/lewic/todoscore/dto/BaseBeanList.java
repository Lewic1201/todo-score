package com.lewic.todoscore.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import lombok.Data;

import java.util.List;

@Data
class BaseBeanList<T> {

    @JsonView(View.Detail.class)
    private Integer pageNum;

    @JsonView(View.Detail.class)
    private Integer count;

    @JsonView(View.Summary.class)
    private List<T> data;

    BaseBeanList() {
    }

    BaseBeanList(Page page, List<T> data) {
        this.count = page.getTotalCount();
        this.pageNum = page.getPageNo();
        this.data = data;
    }

    BaseBeanList(Integer count, Integer pageNum, List<T> data) {
        this.count = count;
        this.pageNum = pageNum;
        this.data = data;
    }
}
