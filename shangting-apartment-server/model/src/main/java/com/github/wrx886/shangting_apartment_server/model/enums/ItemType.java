package com.github.wrx886.shangting_apartment_server.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemType implements BaseEnum {

    APARTMENT(1, "公寓"),

    ROOM(2, "房间");

    @EnumValue // 实现枚举对象到枚举属性的转换，即将 枚举对象 转为 code 属性值
    @JsonValue // 实现枚举对象到 json 的转换，即将 枚举对象 转为 code 属性值
    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return name;
    }

    ItemType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
