package com.dubboss.seckillapi.exception;

import com.dubboss.seckillapi.enums.ResultSk;
import com.dubboss.seckillapi.enums.ResultStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @ClassName GlobalExceptionHandler
 * @Description 省去controller的参数校验
 * @Author yjy
 * @Date 2020/5/12 22:40
 * @Vertion 1.0
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    //对Exception异常进行处理
    @ExceptionHandler(value = Exception.class)
    public ResultSk<String> exceptionHandler(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return ResultSk.error(globalException.getStatus());
        } else if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            //取到所有错误
            List<ObjectError> errors = bindException.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return ResultSk.error(ResultStatus.SESSION_ERROR);
        } else {
            return ResultSk.error(ResultStatus.SYSTEM_ERROR);
        }
    }
}
