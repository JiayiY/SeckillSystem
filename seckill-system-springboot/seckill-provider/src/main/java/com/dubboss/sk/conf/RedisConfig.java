package com.dubboss.sk.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/11 20:37
 * @Vertion 1.0
 **/
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private String port;

    private String host;

    private String timeout;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
}
