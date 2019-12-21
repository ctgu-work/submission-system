package com.ctgu.contributionsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @program: contribution-system *
 * @classname: WebSocketConfig *
 * @author: lnback *
 * @create: 2019-12-20 20:17
 **/
@Configuration
public class WebSocketConfig {
    /**
     * 配置websocket
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
