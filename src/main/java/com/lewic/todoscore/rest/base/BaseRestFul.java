package com.lewic.todoscore.rest.base;

import java.util.List;

/**
 * @author lewic
 * @since 2020/1/4 17:28
 */

public interface BaseRestFul<T> {

    public String list();

    public String show(Integer id) throws Exception;

    public String create(T obj);

    public String edit(T obj) throws Exception;

    public String delete(Integer id) throws Exception;
}
