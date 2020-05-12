package com.dubboss.seckillapi.enums;


import java.io.Serializable;

public class ResultSk<T> extends AbstractResult implements Serializable {
    private static final long serialVersionUID = 867933019328199779L;
    private T data;
    private Integer count;

    protected ResultSk() {
    }

    protected ResultSk(ResultStatus status, String message) {
        super(status, message);
    }

    protected ResultSk(ResultStatus status) {
        super(status);
    }

    public static <T> ResultSk<T> build() {
        return new ResultSk(ResultStatus.SUCCESS, (String) null);
    }

    public static <T> ResultSk<T> build(String message) {
        return new ResultSk(ResultStatus.SUCCESS, message);
    }

    public static <T> ResultSk<T> error(ResultStatus status) {
        return new ResultSk<T>(status);
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
