package com.lewic.todoscore.constant;

/**
 * @author lewic
 * @since 2020/1/5 15:56
 */

public enum ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String value;

    ResponseCode(int code,String value){
        this.code = code;
        this.value = value;
    }

    public int getCode(){
        return code;
    }

    public String getValue(){
        return value;
    }
}