package com.github.wrx886.shangting_apartment_server.web.admin.vo.fee;

import com.github.wrx886.shangting_apartment_server.model.entity.FeeKey;
import com.github.wrx886.shangting_apartment_server.model.entity.FeeValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FeeKeyVo extends FeeKey {

    @Schema(description = "杂费value列表")
    private List<FeeValue> feeValueList;
}
