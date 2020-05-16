package com.dubboss.sk.util;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/14 22:41
 * @Vertion 1.0
 **/
public class UUIDUtil {
    // 生成UUID
    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
