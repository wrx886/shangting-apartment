package com.github.wrx886.shangting_apartment_server.web.admin.vo.attr;

import com.github.wrx886.shangting_apartment_server.model.entity.AttrKey;
import com.github.wrx886.shangting_apartment_server.model.entity.AttrValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AttrKeyVo extends AttrKey {
    @Schema(description = "属性value列表")
    private List<AttrValue> attrValueList;
}
