package com.github.wrx886.shangting_apartment_server.web.admin.vo.room;

import com.github.wrx886.shangting_apartment_server.model.entity.RoomInfo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.graph.GraphVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Schema(description = "房间信息")
@EqualsAndHashCode(callSuper = true)
public class RoomSubmitVo extends RoomInfo {

    @Schema(description = "图片列表")
    private List<GraphVo> graphVoList;

    @Schema(description = "属性信息列表")
    private List<Long> attrValueIds;

    @Schema(description = "配套信息列表")
    private List<Long> facilityInfoIds;

    @Schema(description = "标签信息列表")
    private List<Long> labelInfoIds;

    @Schema(description = "支付方式列表")
    private List<Long> paymentTypeIds;

    @Schema(description = "可选租期列表")
    private List<Long> leaseTermIds;

}
