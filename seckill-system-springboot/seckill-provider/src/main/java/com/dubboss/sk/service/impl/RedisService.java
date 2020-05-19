package com.dubboss.sk.service.impl;

import com.alibaba.fastjson.JSON;
import com.dubboss.sk.redis.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName RedisService
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/11 20:58
 * @Vertion 1.0
 **/
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyPrefix.getPrefix() + key;
            String string = jedis.get(realKey);
            T t = strtoBean(string, clazz);
            return t;
        } finally {
            returntoPool(jedis);
        }
    }

    public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String string = beantoString(value);
            if (string == null || string.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey = keyPrefix.getPrefix() + key;
            int expireTime = keyPrefix.expireSeconds();
            if (expireTime <= 0) {
                jedis.set(realKey, string);
            } else {
                jedis.setex(realKey, expireTime, string);
            }
            return true;
        } finally {
            returntoPool(jedis);
        }
    }

    /**
     * @param keyPrefix
     * @param key
     * @return java.lang.Long
     * @Author yangjiayi
     * @Description //增加值（是原子操作）
     * @Date 22:28 2020/5/11
     */
    public <T> Long incr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returntoPool(jedis);
        }
    }

    /**
     * @param keyPrefix
     * @param key
     * @return java.lang.Long
     * @Author yangjiayi
     * @Description //减少值（原子操作）
     * @Date 22:28 2020/5/11
     */
    public <T> Long decr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returntoPool(jedis);
        }
    }

    /**
     * 删除
     */
    public boolean delete(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            long ret = jedis.del(realKey);
            return ret > 0;
        } finally {
            returntoPool(jedis);
        }
    }

    public <T> boolean exists(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returntoPool(jedis);
        }
    }

    public static <T> String beantoString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + clazz;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + clazz;
        } else {
            return JSON.toJSONString(value);
        }
    }

    public static <T> T strtoBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returntoPool(Jedis jedis) {
        if (jedis != null) {
            // 返回连接到连接池，不是关闭连接
            jedis.close();
        }
    }
}
