package com.code.classsystem.shiro.util;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
@Component
public class TokenUtil {
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader("USER-TOKEN");
        //token 有可能等undefined
        if(!StringUtils.isEmpty(token)&&token.equals("undefined")){
            token=null;
        }
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("USER-TOKEN");
        }
        return token;
    }

    public boolean checkTokenValidate(String token) {
        //todo 等待前后端接口对接时候测试
        Session session = ShiroUtils.getSession();
        Object result = session.getAttribute(token);
        if(result!=null){
            return true;
        }
        return false;
    }
}
