package com.github.wrx886.shangting_apartment_server.web.admin.vo.room;

import com.github.wrx886.shangting_apartment_server.web.admin.vo.attr.AttrValueVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.graph.GraphVo;
import com.github.wrx886.shangting_apartment_server.model.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Schema(description = "房间信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoomDetailVo extends RoomInfo {

    @Schema(description = "所属公寓信息")
    private ApartmentInfo apartmentInfo;

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "属性信息列表")
    private List<AttrValueVo> attrValueVoList;

    @Schema(description = "配套信息列表")
    private List<FacilityInfo> facilityInfoList;

    @Schema(description = "标签信息列表")
    private List<LabelInfo> labelInfoList;

    @Schema(description = "支付方式列表")
    private List<PaymentType> paymentTypeList;

    @Schema(description = "可选租期列表")
    private List<LeaseTerm> leaseTermList;
}
