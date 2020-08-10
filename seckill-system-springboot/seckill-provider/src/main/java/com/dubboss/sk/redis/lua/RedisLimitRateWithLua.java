package com.dubboss.sk.redis.lua;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName RedisLimitRateWithLua
 * @Description TODO
 * @Author yjy
 * @Date 2020/8/10 10:23
 * @Vertion 1.0
 **/
public class RedisLimitRateWithLua {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    latch.await();
                    System.out.println("请求是否被执行：" + acquire());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        latch.countDown();
    }

    public static boolean acquire() throws IOException, URISyntaxException{
        Jedis jedis = new Jedis("127.0.0.1");
        String lua =
                "local key = KEYS[1] " +
                        " local limit = tonumber(ARGV[1]) " +
                        " local current = tonumber(redis.call('get', key) or '0')" +
                        " if current + 1 > limit " +
                        " then  return 0 " +
                        " else " +
                        " redis.call('INCRBY', key,'1')" +
                        " redis.call('expire', key,'10') " +
                        " end return 1 ";
        // 当前秒
        String key = "ip:" + System.currentTimeMillis() / 1000;
        // 最大限制
        String limit = "5";
        List<String> keys = new ArrayList<>();
        keys.add(key);
        List<String> args = new ArrayList<>();
        args.add(limit);
        jedis.auth("1021");
        String luaScript = jedis.scriptLoad(lua);
        Long result = (Long) jedis.evalsha(luaScript, keys, args);
        return result == 1;
    }
}