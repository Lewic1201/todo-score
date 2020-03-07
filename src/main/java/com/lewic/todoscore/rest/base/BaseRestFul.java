package com.lewic.todoscore.rest.base;

import com.lewic.todoscore.common.ApiResult;
import com.lewic.todoscore.entity.Task;

import java.util.List;

/**
 * @author lewic
 * @since 2020/1/4 17:28
 */

public interface BaseRestFul<T> {

    public ApiResult<List<Task>> list() throws Exception;

    public ApiResult<Task> show(Integer id) throws Exception;

    public ApiResult create(T obj) throws Exception;

    public ApiResult edit(T obj) throws Exception;

    public ApiResult delete(Integer id) throws Exception;
}
