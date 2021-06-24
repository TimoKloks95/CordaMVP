package com.template.webserver;

import com.template.webserver.config.ClientConfiguration;
import com.template.webserver.services.impl.ContractServiceImpl;
import com.template.webserver.services.impl.RPCServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.security.auth.login.AppConfigurationEntry;

import static org.springframework.boot.WebApplicationType.SERVLET;

/**
 * Our Spring Boot application.
 */
@SpringBootApplication
public class Starter {

    private static Logger log = LoggerFactory.getLogger(Starter.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Starter.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setWebApplicationType(SERVLET);
        ConfigurableApplicationContext context = app.run(args);
        ClientConfiguration appConfiguration = context.getBean(ClientConfiguration.class);
    }
}