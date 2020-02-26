package com.lewic.todoscore.common;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@SuppressWarnings("rawtypes")
public class ApiResult<T> {

    private Integer code = ResponseCode.SUCCESS.getCode();

    private String message = ResponseCode.SUCCESS.getMessage();

    private T data;

    private Boolean success = true;

    /**
     * 成功时的静态方法
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<>();
    }

    public static <T> ApiResult<T> success(T data) {
        return ApiResult.success(data, ResponseCode.SUCCESS);
    }

    public static <T> ApiResult<T> success(ResponseCode result) {
        return ApiResult.success(null, result);
    }

    public static <T> ApiResult<T> success(T data, ResponseCode result) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setData(data);
        apiResult.setCode(result.getCode());
        apiResult.setMessage(result.getMessage());
        return apiResult;
    }

    /**
     * 失败时的静态方法
     */
    public static <T> ApiResult<T> error() {
        return ApiResult.error(ResponseCode.INTERNAL_FAIL);
    }

    public static <T> ApiResult<T> error(ResponseCode result) {
        return ApiResult.error(result.getCode(), result.getMessage());
    }

    public static <T> ApiResult<T> error(Integer errorCode, String errorMessage) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setData(null);
        apiResult.setCode(errorCode);
        apiResult.setMessage(errorMessage);
        apiResult.setSuccess(false);
        return apiResult;
    }

}