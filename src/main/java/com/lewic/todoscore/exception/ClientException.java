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
        super(ResponseCode.UNKNOW_ERROR.getMessage());
        this.errorCode = ResponseCode.UNKNOW_ERROR.getCode();
        this.errorMessage = ResponseCode.UNKNOW_ERROR.getMessage();
        log.error(ResponseCode.UNKNOW_ERROR.getMessage());
    }

    public ClientException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.errorCode = responseCode.getCode();
        this.errorMessage = responseCode.getMessage();
        log.error(responseCode.getMessage());
    }

    public ClientException(ResponseCode responseCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = responseCode.getCode();
        this.errorMessage = errorMessage;
        log.error(errorMessage);
    }

    public ClientException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        log.error(errorMessage);
    }

}
