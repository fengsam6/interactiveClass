package com.code.core.exception;


import com.code.core.enums.ErrorEnum;
import com.code.core.error.CommonError;

/**
 * Created by rf on 2019/4/23.
 */
public class AuthenticationFailException extends RuntimeException implements CommonError {
    private int code;
    private String msg;
    public AuthenticationFailException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public AuthenticationFailException(ErrorEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMsg());
    }
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
