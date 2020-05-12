package com.code.classsystem.common.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.common.shiro.util.TokenUtil;
import com.code.core.entity.ResponseResult;
import com.code.core.enums.ErrorEnum;
import com.code.core.util.ResponseResultUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Component
public class LoginFilter extends FormAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    @Autowired
    private TokenUtil tokenUtil;

    @Value("${system.openTestEnv}")
    private Boolean openTestEnv = false;

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        //过滤前端OPTIONS请求
        if (req.getMethod().toUpperCase(Locale.ENGLISH).equals("OPTIONS".toUpperCase(Locale.ENGLISH))) {
            return true;
        }

        //判断用户是否登录
        boolean tokenFlag = ShiroUtils.isLogin();
//        token有效，更新token在redis中存活时间
        if (tokenFlag) {
            logger.debug("----------token有效------------");
            return true;
        } else {
            if (openTestEnv) {
                logger.debug("----------token无效，开发环境已经开启，不校验token有效，方便前端访问后端swagger ui接口------------");
                return true;
            }
            //返回false，底层会调用redirectToLogin返回json格式数据
            return false;
        }
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        //用户未登录返回json格式
        logger.info("----------------------用户认证失败----------");
        printJsonMsg((HttpServletResponse) response, ErrorEnum.USER_NOT_LOGIN);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResp = WebUtils.toHttp(response);
        HttpServletRequest httpReq = WebUtils.toHttp(request);
        httpResp.setContentType("application/json; charset=utf-8");
        /*系统重定向会默认把请求头清空，这里通过拦截器重新设置请求头，解决跨域问题*/
        String origin = httpReq.getHeader("Origin");
        httpResp.addHeader("Access-Control-Allow-Origin", StringUtils.isEmpty(origin) ? "" : origin);
        httpResp.addHeader("Access-Control-Allow-Headers", "*");
        httpResp.addHeader("Access-Control-Allow-Methods", "*");
        httpResp.addHeader("Access-Control-Allow-Credentials", "true");
        this.redirectToLogin(request, response);
        return false;
    }

    private void printJsonMsg(HttpServletResponse response, ErrorEnum errorEnum) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        ResponseResult responseResult = ResponseResultUtil.renderError(errorEnum);
        String errorMsg = JSON.toJSONString(responseResult);
        response.getWriter().print(errorMsg);
    }
}
