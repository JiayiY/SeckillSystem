package util;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;

/**
 * @ClassName JWTUtil
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/6 22:11
 * @Vertion 1.0
 **/
public class MD5Utils {


    private static final String GET_SALT = getSaltT();

    public static String getSaltT() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[15];
        random.nextBytes(bytes);
        String salt = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
        return salt;
    }

    public static String MD5(String keyName) {
        /**
         * 返回16
         */
        return DigestUtils.md5Hex(keyName);

    }


    /**
     * 测试使用
     *
     * @param inputPass
     * @return
     */
    public static String inputPassFormPass(String inputPass) {
        String str = "" + GET_SALT.charAt(0) + GET_SALT.charAt(2) + inputPass + GET_SALT.charAt(4) + GET_SALT.charAt(6);
        return MD5(str);
    }

    /**
     * 盐值salt 随机 二次加密
     *
     * @param inputPass
     * @return
     */
    public static String formPassFormPass(String inputPass) {
        String str = "" + GET_SALT.charAt(0) + GET_SALT.charAt(2) + inputPass + GET_SALT.charAt(4) + GET_SALT.charAt(6);
        return MD5(str);
    }

    /**
     * 第二次md5--反解密 用户登录验证 ---　salt　可随机
     *
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(4) + salt.charAt(6);
        return MD5(str);
    }

}
