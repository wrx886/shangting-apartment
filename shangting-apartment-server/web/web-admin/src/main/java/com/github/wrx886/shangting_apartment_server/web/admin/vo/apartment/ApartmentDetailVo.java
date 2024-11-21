package com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment;

import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.FacilityInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.LabelInfo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.graph.GraphVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.fee.FeeValueVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Schema(description = "公寓信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class ApartmentDetailVo extends ApartmentInfo {

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "标签列表")
    private List<LabelInfo> labelInfoList;

    @Schema(description = "配套列表")
    private List<FacilityInfo> facilityInfoList;

    @Schema(description = "杂费列表")
    private List<FeeValueVo> feeValueVoList;

}
