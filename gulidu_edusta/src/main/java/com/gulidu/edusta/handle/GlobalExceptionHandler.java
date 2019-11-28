package com.gulidu.edusta.handle;

import com.guli.educommom.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        return R.error().message("执行一般异常");
    }
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        return R.error().message("执行特殊异常");
    }
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public R error(EduException e){
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}
