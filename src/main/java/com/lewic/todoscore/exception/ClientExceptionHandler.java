package com.lewic.todoscore.exception;

import com.lewic.todoscore.common.ApiResult;
import com.lewic.todoscore.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理类
 * todo 返回值可能需要统一改成json格式
 *
 * @author lewic
 * @since 2020/2/26 20:47
 */

@ControllerAdvice
@Slf4j
public class ClientExceptionHandler {

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = ClientException.class)
    @ResponseBody
    public ApiResult clientExceptionHandler(HttpServletRequest req, HttpServletResponse res, ClientException e) {
        log.error("发生业务异常！原因是：{}", e.getErrorMessage());
        res.setStatus(e.getErrorCode());
        return ApiResult.error(e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ApiResult exceptionHandler(HttpServletRequest req, HttpServletResponse res, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        res.setStatus(ResponseCode.PARAMETER_ERROR.getCode());
        return ApiResult.error(ResponseCode.PARAMETER_ERROR);
    }

    /**
     * 处理请求方法不支持的异常
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ApiResult exceptionHandler(HttpServletRequest req, HttpServletResponse res,
                                      HttpRequestMethodNotSupportedException e) {
        log.error("发生请求方法不支持异常！原因是:", e);
        res.setStatus(ResponseCode.REQUEST_METHOD_SUPPORT_ERROR.getCode());
        return ApiResult.error(ResponseCode.REQUEST_METHOD_SUPPORT_ERROR);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult exceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        log.error("未知异常！原因是:", e);
        res.setStatus(ResponseCode.INTERNAL_FAIL.getCode());
        return ApiResult.error(ResponseCode.INTERNAL_FAIL);
    }

}
