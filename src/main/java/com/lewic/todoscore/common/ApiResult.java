package com.lewic.todoscore.common;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@SuppressWarnings("rawtypes")
public class ApiResult<T> {

    private int code = ResultEnum.SUCCESS.getCode();

    private String message = ResultEnum.SUCCESS.getMessage();

    private T data;

    private Boolean success = true;

    /**
     * 成功时的静态方法
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<>();
    }

    public static <T> ApiResult<T> success(T data) {
        return ApiResult.success(data, ResultEnum.SUCCESS);
    }

    public static <T> ApiResult<T> success(T data, ResultEnum result) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setData(data);
        apiResult.setCode(result.getCode());
        apiResult.setMessage(result.getMessage());
        return apiResult;
    }

    /**
     * 失败时的静态方法
     */
    public static <T> ApiResult<T> fail() {
        return ApiResult.fail(null);
    }

    public static <T> ApiResult<T> fail(T data) {
        return ApiResult.fail(data, ResultEnum.FAIL);
    }

    public static <T> ApiResult<T> fail(T data, ResultEnum result) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setData(data);
        apiResult.setCode(result.getCode());
        apiResult.setMessage(result.getMessage());
        apiResult.setSuccess(false);
        return apiResult;
    }

}