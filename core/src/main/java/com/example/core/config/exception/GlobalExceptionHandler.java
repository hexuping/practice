package com.example.core.config.exception;

import com.example.core.entity.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler<T> {

    @ExceptionHandler(RuntimeException.class)
    public Result<T> runtimeExceptionHandler(RuntimeException ex) {
        return Result.error("100000", ex.getMessage());
    }
}
