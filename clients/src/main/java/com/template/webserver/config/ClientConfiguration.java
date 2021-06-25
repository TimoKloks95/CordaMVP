package com.template.webserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
public class ClientConfiguration {
    @Value("${config.rpc.host}")
    private String host;

    @Value("${config.rpc.port}")
    private String port;

    @Value("${config.rpc.username}")
    private String username;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Value("${config.rpc.password}")
    private String password;

}
