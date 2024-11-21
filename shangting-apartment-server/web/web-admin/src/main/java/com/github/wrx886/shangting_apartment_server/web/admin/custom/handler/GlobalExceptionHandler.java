package com.github.wrx886.shangting_apartment_server.web.admin.custom.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.wrx886.shangting_apartment_server.common.result.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 捕获所有异常
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        // 自定义处理异常
        e.printStackTrace();
        return Result.fail();
    }
}