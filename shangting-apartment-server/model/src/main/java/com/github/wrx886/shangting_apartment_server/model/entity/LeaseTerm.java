package com.github.wrx886.shangting_apartment_server.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName lease_term
 */
@TableName(value = "lease_term")
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "租期信息")
public class LeaseTerm extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "租期月数")
    @TableField("month_count")
    private Integer monthCount;

    @Schema(description = "租期单位:月")
    @TableField("unit")
    private String unit;
}