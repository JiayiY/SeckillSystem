package com.dubboss.sk.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName RedisPoolFactory
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/11 21:47
 * @Vertion 1.0
 **/
@Service
public class RedisPoolFactory {

    @Autowired
    RedisConfig redisConfig;

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        return new JedisPool(jedisPoolConfig, redisConfig.getHost());
    }
}
