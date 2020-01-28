package com.lewic.todoscore.rest;

import java.util.List;

/**
 * @author lewic
 * @since 2020/1/4 17:28
 */

public interface BaseRestFul<T> {

    public String list();

    public String show(Integer id);

    public String create(T obj);

    public String edit(T obj);

    public String delete(Integer id);
}
