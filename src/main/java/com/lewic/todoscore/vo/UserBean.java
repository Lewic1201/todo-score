package com.lewic.todoscore.vo;

import com.lewic.todoscore.entity.User;
import com.lewic.todoscore.vo.base.BaseBeanList;
import org.springframework.data.domain.Page;

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

    public UserBean(Integer count, List<User> data) {
        super(count, data);
    }
}
