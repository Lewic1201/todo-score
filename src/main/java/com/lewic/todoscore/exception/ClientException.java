package com.lewic.todoscore.exception;

import com.lewic.todoscore.common.ResponseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author lewic
 * @since 2020/2/26 14:48
 */
@Getter
@Setter
@Slf4j
public class ClientException extends Exception implements Serializable {

    private static final long serialVersionUID = -8043138751367341663L;

    private Integer errorCode;

    private String errorMessage;

    public ClientException() {
        this(ResponseCode.UNKNOWN_ERROR);
    }

    public ClientException(ResponseCode responseCode) {
        this(responseCode.getCode(), responseCode.getMessage());
    }

    public ClientException(ResponseCode responseCode, String errorMessage) {
        this(responseCode.getCode(), errorMessage);
    }

    public ClientException(Integer errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        log.error(errorMessage);
    }

    public ClientException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        log.error(errorMessage);
    }

    // 参考https://blog.csdn.net/yangkai_hudong/article/details/18409007
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
