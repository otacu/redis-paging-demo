package com.example.redis.paging.demo.config;

import com.example.redis.paging.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    @Autowired
    private JedisConfigProperties jedisConfigProperties;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(jedisConfigProperties.getMaxTotal());
        config.setMaxIdle(jedisConfigProperties.getMaxIdle());
        config.setMaxWaitMillis(jedisConfigProperties.getMaxWaitMillis());
        JedisPool jedisPool;
        if ("".equals(jedisConfigProperties.getPassword())) {
            jedisPool = new JedisPool(config, jedisConfigProperties.getHost(), jedisConfigProperties.getPort(), jedisConfigProperties.getTimeOut());
        } else {
            jedisPool = new JedisPool(config, jedisConfigProperties.getHost(), jedisConfigProperties.getPort(), jedisConfigProperties.getTimeOut(), jedisConfigProperties.getPassword());
        }
        // 此处为RedisUtil设置了jedisPool
        RedisUtil.setJedisPool(jedisPool);
        return jedisPool;
    }
}
