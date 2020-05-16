package com.dubboss.sk.redis;

/**
 * @ClassName BasePrefix
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/11 21:54
 * @Vertion 1.0
 **/
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * @param prefix
     * @return
     * @Author yangjiayi
     * @Description //0代表永不过期
     * @Date 22:09 2020/5/11
     */
    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    /**
     * @param
     * @return java.lang.String
     * @Author yangjiayi
     * @Description //确保每一个key是唯一的
     * @Date 22:07 2020/5/11
     */
    @Override
    public String getPrefix() {
        // getsimplename 和 getname 的区别?
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
