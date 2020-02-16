package com.lewic.todoscore.vo.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.lewic.todoscore.common.View;
import com.lewic.todoscore.common.Page;
import lombok.Data;

import java.util.List;

@Data
public class BaseBeanList<T> {

    @JsonView(View.Detail.class)
    private Integer count;

    @JsonView(View.Summary.class)
    private List<T> data;

    public BaseBeanList() {
    }

    public BaseBeanList(Page page, List<T> data) {
        this.count = page.getTotalCount();
        this.data = data;
    }

    public BaseBeanList(Integer count, List<T> data) {
        this.count = count;
        this.data = data;
    }
}
