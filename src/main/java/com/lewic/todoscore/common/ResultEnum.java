package com.lewic.todoscore.common;

import lombok.Getter;

/**
 * <p>Description: 响应结果枚举</p>
 *
 * @version v0.1
 */
@Getter
public enum ResultEnum {

    /**
     * 响应成功
     */
    SUCCESS(200, "成功"),

    /**
     * 服务器已接受请求，但尚未处理 .已提交，用于异步操作，但暂时无返回结果
     */ //	TODO
    RECEIVED(202, "成功"),

    /**
     * 参数出错
     */ //	TODO
    PARAMETER_ERROR(400, "参数出错"),
    /**
     * 请求参数缺失
     */
    PARAM_MISS(400, "请求参数缺失"),

    /**
     * 认证问题
     */ //	TODO
    AUTHENTICATION_ERROR(401, "认证出错"),

    /**
     * 方法不存在问题
     */ //	TODO
    resource_nonexistent(404, "资源不存在"),

    /**
     * 内部失败
     */
    FAIL(500, "系统出错"),


    /**
     * 系统未知错误
     */
    UNKNOW_ERROR(-1, "未知错误"),


    //-----------------------------------------//


    /**
     * 登录用户名或密码错误
     */
    USER_INFO_ERROR(5001, "登录用户名或密码错误"),

    /**
     * 查询结果为空
     */
    RESULT_EMPTY(5002, "查询结果为空"),

    /**
     * 更新数据异常
     */
    UPDATE_ERROR(5003, "更新数据异常"),

    /**
     * 用户名已存在
     */
    USER_NAME_EXISTS(5004, "用户名已存在"),

    /**
     * 不满足分派条件
     */
    NO_SUFFICIENT_ASSIGN(5005, "不满足分派条件，请检查分配目录、用户和数量是否正确"),

    /**
     * 数据插入失败
     */
    INSERT_ERROR(5006, "数据插入失败"),

    /**
     * 无效的字符
     */
    INVALIDATE_CHARACTER(5007, "路径中存在无效的字符.号"),

    /**
     * 任务中缺少需要识别的图片
     */
    NO_IMAGE_PATH(5008, "任务中缺少需要识别的图片,请选择图片进行识别"),

    /**
     * 任务中缺少需要识别的图片
     */
    NO_DEFECT_TYPE(5009, "任务中缺少需要识别的缺陷类型,请选择需要识别的缺陷类型"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
