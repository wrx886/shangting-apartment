package com.github.wrx886.shangting_apartment_server.web.admin.vo.attr;

import com.github.wrx886.shangting_apartment_server.model.entity.AttrValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "属性值")
@Data
@EqualsAndHashCode(callSuper = true)
public class AttrValueVo extends AttrValue {

    @Schema(description = "对应的属性key_name")
    private String attrKeyName;
}
