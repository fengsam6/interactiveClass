package com.code.core.exception.handler;


import com.code.core.entity.ResponseResult;
import com.code.core.enums.ErrorEnum;
import com.code.core.exception.AuthenticationFailException;
import com.code.core.exception.BusinessException;
import com.code.core.exception.FileException;
import com.code.core.exception.ParamInvalidException;
import com.code.core.util.ResponseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public ResponseResult handleBindException(BindException e) {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = e.getFieldError();
        String msg = fieldError.getDefaultMessage();
        log.error("--------错误提示{}:{}---- ----", e.getClass().getName(), msg);
        e.printStackTrace();
        // 生成返回结果
        return ResponseResultUtil.renderError(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(msg));
    }


    @ExceptionHandler(AuthenticationFailException.class)
    public ResponseResult handleAuthenticationFailException(AuthenticationFailException e) {
        log.error("--------{}错误提示: {}---- ----", e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return ResponseResultUtil.renderError(e);
    }

    @ExceptionHandler(ParamInvalidException.class)
    public ResponseResult paramInvalidExceptionHandler(ParamInvalidException e) {
        log.error("--------{}错误提示: {}---- ----", e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return ResponseResultUtil.renderError(e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("--------{}错误提示: {}---- ----", e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return ResponseResultUtil.renderError(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("----------{}---------------------------", e.getMessage());
        e.printStackTrace();
        return ResponseResultUtil.renderError(ErrorEnum.INVALIDATE_PARAM_EXCEPTION.setMsg(e.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseResult businessExceptionHandler(BusinessException e) {
        log.error("--------{}错误提示: {}---- ----", e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return ResponseResultUtil.renderError(e);
    }

    @ExceptionHandler(FileException.class)
    public ResponseResult businessExceptionHandler(FileException e) {
        log.error("--------{}错误提示: {}---- ----", e.getClass().getName(), e.getMessage());
        e.printStackTrace();
        return ResponseResultUtil.renderError(e);
    }


    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e, HttpServletRequest request) {
        log.error("--------{}错误提示: {}---- ----", e.getClass().getName(), e.getMessage());
        if (e instanceof NoHandlerFoundException) {
            String errorMsg = String.format("请求url %s不存在！", request.getRequestURI());
            log.error(errorMsg);
            return ResponseResultUtil.renderError(ErrorEnum.PAGE_NOT_FOUND.setMsg(errorMsg));
        }
        e.printStackTrace();
        return ResponseResultUtil.renderError(ErrorEnum.UN_KNOW_EXCEPTION);
    }
}
