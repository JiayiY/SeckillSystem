package shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName JWTToken
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/6 22:21
 * @Vertion 1.0
 **/
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
