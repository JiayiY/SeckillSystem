package exception;

/**
 * @ClassName UnauthorizedException
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/6 22:28
 * @Vertion 1.0
 **/
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
