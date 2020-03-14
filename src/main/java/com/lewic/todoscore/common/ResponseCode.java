package com.lewic.todoscore.common;

import lombok.Getter;

/**
 * @author lewic
 * @since 2020/1/5 15:56
 */
@Getter
public enum ResponseCode {

    /**
     * 响应成功
     */
    SUCCESS(200, "成功"),

    /**
     * 服务器已接受请求，但尚未处理 .已提交，用于异步操作，但暂时无返回结果
     */
    RECEIVED(202, "成功"),

    /**
     * 参数出错
     */
    PARAMETER_ERROR(400, "参数出错"),

    /**
     * 请求参数缺失
     */
    PARAM_MISS(400, "请求参数缺失"),

    /**
     * 认证问题
     */
    AUTHENTICATION_ERROR(401, "认证出错"),

    /**
     * 方法不存在问题
     */
    resource_nonexistent(404, "资源不存在"),

    /**
     * 内部失败
     */
    INTERNAL_FAIL(500, "系统出错"),

    /**
     * 系统未知错误
     */
    UNKNOWN_ERROR(-1, "未知错误"),


    //-----------------------------------------//


    /**
     * 登录用户名或密码错误
     */
    USER_INFO_ERROR(1001, "登录用户名或密码错误"),

    /**
     * 用户名已存在
     */
    USER_EXISTS(1002, "用户名已存在"),

    /**
     * 用户名不存在
     */
    USER_NO_EXISTS(1003, "用户名不存在"),

    /**
     * 查询结果为空
     */
    RESULT_EMPTY(1011, "查询结果为空"),

    /**
     * 数据插入失败
     */
    INSERT_ERROR(1012, "数据插入失败"),

    /**
     * 更新数据异常
     */
    UPDATE_ERROR(1013, "更新数据异常"),

    /**
     * 更新数据异常
     */
    SELECT_ERROR(1015, "获取后台数据异常"),

    /**
     * 系统数据校验出错
     */
    PARAM_CHECK_ERROR(1014, "数据插入失败"),

    /**
     * 不满足分派条件
     */
    NO_SUFFICIENT_ASSIGN(1021, "不满足分派条件，请检查分配目录、用户和数量是否正确"),

    /**
     * 无效的字符
     */
    INVALIDATE_CHARACTER(1022, "路径中存在无效的字符"),

    /**
     * 任务中缺少需要识别的图片
     */
    NO_IMAGE_PATH(1031, "任务中缺少需要识别的图片,请选择图片进行识别"),

    /**
     * 任务中缺少需要识别的图片
     */
    NO_DEFECT_TYPE(1032, "任务中缺少需要识别的缺陷类型,请选择需要识别的缺陷类型"),

    /**
     * 添加请求跟踪记录出错
     */
    NO_REQ_TRACE(1033, "获取请求信息出错"),

    /**
     * 当前请求方法不支持
     */
    REQUEST_METHOD_SUPPORT_ERROR(40001, "当前请求方法不支持");

    private final Integer code;
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}