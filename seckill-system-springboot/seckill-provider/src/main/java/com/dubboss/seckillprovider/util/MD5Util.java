package com.dubboss.seckillprovider.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ClassName MD5Utils
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/11 22:50
 * @Vertion 1.0
 **/
public class MD5Util {
    private static final String salt = "1a2b3c4d";

    private String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public String inputPwdToFormPwd(String pwd) {
        String newPwd = "" + salt.charAt(0) + salt.charAt(2) + pwd + salt.charAt(5) + salt.charAt(4);
        return md5(newPwd);
    }

    public String formPwdToDBPwd(String formPwd, String salt) {
        String newPwd = "" + salt.charAt(0) + salt.charAt(2) + formPwd + salt.charAt(5) + salt.charAt(4);
        return md5(newPwd);
    }

    public String inputPwdToDBPwd(String pwd, String saltDB) {
        String dbPwd = formPwdToDBPwd(inputPwdToFormPwd(pwd), saltDB);
        return md5(dbPwd);
    }
}
