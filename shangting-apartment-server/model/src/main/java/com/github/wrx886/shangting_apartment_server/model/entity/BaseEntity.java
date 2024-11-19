package com.github.wrx886.shangting_apartment_server.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT) // 这里配置为 插入数据 时自动填充
    @JsonIgnore
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE) // 这里配置为 更新数据 时自动填充
    @JsonIgnore
    private Date updateTime;

    @TableLogic
    @Schema(description = "逻辑删除")
    @TableField(value = "is_deleted")
    @JsonIgnore
    private Byte isDeleted;
}