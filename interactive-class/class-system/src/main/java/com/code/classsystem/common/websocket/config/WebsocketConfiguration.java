package com.code.classsystem.common.websocket.config;

import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

@Configuration
public class WebsocketConfiguration extends ServerEndpointConfig.Configurator {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 将用户信息存储到socket的配置里
        User userEntity = ShiroUtils.getUserEntity();
        if (userEntity != null) {
            sec.getUserProperties().put("user", userEntity);
            super.modifyHandshake(sec, request, response);
        }
    }
}
