package com.dubboss.seckillprovider.redis;

/**
 * @ClassName KeyPrefix
 * @Description 对各个模块做区分
 * @Author yjy
 * @Date 2020/5/11 21:53
 * @Vertion 1.0
 **/
public interface KeyPrefix {

    /**
     * @param
     * @return int
     * @Author yangjiayi
     * @Description //过期时间
     * @Date 22:32 2020/5/11
     */
    public int expireSeconds();

    /**
     * @param
     * @return java.lang.String
     * @Author yangjiayi
     * @Description //key前缀
     * @Date 22:33 2020/5/11
     */
    public String getPrefix();
}
