package com.code.classsystem.common.shiro.handler;


import com.code.core.entity.ResponseResult;
import com.code.core.enums.ErrorEnum;
import com.code.core.util.ResponseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ShiroExceptionHandler {


    /**
     * 处理shiro权限不够
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseResult handleUnauthorizedException(UnauthorizedException e) {
        String message = e.getMessage();
        log.error("--------{}错误提示: {}---- ----", e.getClass().getName(), message);
        e.printStackTrace();
        return ResponseResultUtil.renderError(ErrorEnum.UNAUTHORIZED.setMsg("权限不够"));
    }
}
