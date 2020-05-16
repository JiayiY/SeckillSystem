package com.dubboss.sk.exception;

import com.dubboss.sk.enums.ResultStatus;

/**
 * @ClassName GlobalException
 * @Description TODO
 * @Author yjy
 * @Date 2020/5/12 23:00
 * @Vertion 1.0
 **/
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ResultStatus status;

    public GlobalException(ResultStatus status) {
        super();
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}
