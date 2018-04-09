package com.company.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @Author: gaobaozong
 * @Description: 权限 缓存统一类
 * @Date: Created in 2018/1/5 - 17:31
 * @Version: V1.0-SNAPSHOT
 */
@Component
@Slf4j
public class AuthCacheUtil {
    @Autowired
    private JedisUtils jedisUtils;

    public String get(String key){
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisUtils.getResource();
            value = jedis.get(key);
            log.info("cache read {}", value);
        } catch (Exception e) {
            log.warn("读取cache失败", e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
        return value;
    }

    public void set(String key, String value, int seconds){
        Jedis jedis = null;
        try {
            jedis = jedisUtils.getResource();
            jedis.setex(key, seconds,value);
            log.info("cache write {}:{}", key, value);
        } catch (Exception e) {
            log.warn("写入cache失败", e);
        } finally {
            jedisUtils.returnResource(jedis);
        }
    }

}
