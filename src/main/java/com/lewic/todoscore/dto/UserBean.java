package com.lewic.todoscore.dto;

import com.lewic.todoscore.common.Page;
import com.lewic.todoscore.dto.base.BaseBeanList;
import com.lewic.todoscore.entity.mybatis.vo.User;

import java.util.List;

/**
 * @author lewic
 * @since 2020/1/4 19:54
 */

public class UserBean extends BaseBeanList<User> {

    public UserBean() {
    }

    public UserBean(Page page, List<User> data) {
        super(page, data);
    }

    public UserBean(Integer count, Integer pageNum, List<User> data) {
        super(count, pageNum, data);
    }
}
