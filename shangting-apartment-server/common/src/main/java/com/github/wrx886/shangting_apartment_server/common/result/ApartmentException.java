package com.github.wrx886.shangting_apartment_server.common.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApartmentException extends RuntimeException {
    private final Integer code;

    public ApartmentException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ApartmentException(ResultCodeEnum resultCodeEnum) {
        this(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }
}
