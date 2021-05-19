package com.example.redis.paging.demo.util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Slf4j
public class RedisUtil {

    private static JedisPool jedisPool;

    public static void setJedisPool(JedisPool jedisPool) {
        RedisUtil.jedisPool = jedisPool;
    }

    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return null;
    }

    public static void set(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, val);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
    }

    public static void hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field,value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
    }

    public static String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hget(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return null;
    }

    public static void zadd(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.zadd(key, score, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
    }

    public static Set<String> zrange(String key, long start, long stop) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrange(key, start, stop);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return null;
    }

    public static Long zcard(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zcard(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return null;
    }

    private static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
