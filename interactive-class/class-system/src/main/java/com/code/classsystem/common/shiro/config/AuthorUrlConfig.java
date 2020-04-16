package com.code.classsystem.common.shiro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * shiro 请求url控制
 */
@Configuration
public class AuthorUrlConfig {

    @Value("${system.shiro.loginUrl}")
    private String loginUrl;
    @Value("${system.shiro.doLoginUrl}")
    private String  doLoginUrl;
    @Value("${system.shiro.loginOutUrl}")
    private String  logoutUrl;
    @Value("${system.shiro.whiteUrl}")
    private String whiteUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getDoLoginUrl() {
        return doLoginUrl;
    }

    public String getLogoutUrl() {
      return   logoutUrl;
    }

    public String[] getWhiteUrlArray() {
        if(StringUtils.isEmpty(whiteUrl)){
            return null;
        }
        if(whiteUrl.contains(",")){
            return whiteUrl.split(",");
        }else {
            return new String[]{whiteUrl};
        }
    }
}
