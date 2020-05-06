package resultbean;

import enums.ResultStatus;

import java.io.Serializable;

/**
 * @ClassName ResultS
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/6 23:03
 * @Vertion 1.0
 **/
public class ResultS<T> extends AbstractResult implements Serializable {
    private T data;
    private Integer count;

    protected ResultS() {
    }
    protected ResultS(ResultStatus status, String message) {
        super(status, message);
    }
    protected ResultS(ResultStatus status) {
        super(status);
    }
    public static <T> ResultS<T> build() {
        return new ResultS(ResultStatus.SUCCESS, "构造函数");
    }

    public static <T> ResultS<T> build(String message) {
        return new ResultS(ResultStatus.SUCCESS, message);
    }

    public static <T> ResultS<T> error(ResultStatus status) {
        return new ResultS<T>(status);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void success(T value) {
        this.success();
        this.data = value;
        this.count = 0;
    }

}
