package com.github.wrx886.shangting_apartment_server.web.admin.custom.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.wrx886.shangting_apartment_server.common.result.ApartmentException;
import com.github.wrx886.shangting_apartment_server.common.result.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 捕获所有异常
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        // 自定义处理异常
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

    // 处理公寓异常
    @ExceptionHandler(ApartmentException.class)
    public Result<Void> handleApartmentException(ApartmentException ae) {
        return Result.build(ae.getCode(), ae.getMessage());
    }
}