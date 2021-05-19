package com.example.redis.paging.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis.server")
@PropertySource("classpath:redis.properties")
public class JedisConfigProperties {

    private String host;

    private int port;

    private String password;

    private int maxTotal;

    private int maxIdle;

    private int maxWaitMillis;

    private int timeOut;

}
