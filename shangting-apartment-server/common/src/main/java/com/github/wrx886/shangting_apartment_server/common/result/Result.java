package com.github.wrx886.shangting_apartment_server.common.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "全局统一返回结果类")
public class Result<T> {

    @Schema(description = "返回码")
    private Integer code;

    @Schema(description = "返回消息")
    private String message;

    @Schema(description = "返回数据")
    private T data;

    public Result() {
    }

    private static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static Result<Void> build(Integer code, String message) {
        Result<Void> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    public static <T> Result<T> fail() {
        return Result.fail(null);
    }

    public static <T> Result<T> fail(T date) {
        return build(date, ResultCodeEnum.FAIL);
    }
}
