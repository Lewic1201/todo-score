package com.lewic.todoscore.rest;

import java.util.List;

/**
 * @author lewic
 * @since 2020/1/4 17:28
 */

public interface BaseRestFul<T> {

    public T get(Integer id);

    public List<T> list(Integer id);

    public void create(T obj);

    public void update(T obj);

    public void delete(Integer id);
}
