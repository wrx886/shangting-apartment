package com.github.wrx886.shangting_apartment_server.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "杂项费用名称表")
@TableName(value = "fee_key")
@Data
@EqualsAndHashCode(callSuper = true)
public class FeeKey extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "付款项key")
    @TableField(value = "name")
    private String name;

}