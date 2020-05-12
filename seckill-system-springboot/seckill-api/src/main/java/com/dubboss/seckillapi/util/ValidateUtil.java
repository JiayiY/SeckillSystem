package com.dubboss.seckillapi.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName ValidateUtil
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/12 7:38
 * @Vertion 1.0
 **/
public class ValidateUtil {

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String tel) {
        if (StringUtils.isEmpty(tel)) {
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(tel);
        return matcher.matches();
    }
}
