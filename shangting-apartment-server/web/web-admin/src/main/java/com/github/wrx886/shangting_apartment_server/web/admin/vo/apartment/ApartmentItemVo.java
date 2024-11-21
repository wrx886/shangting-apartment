package com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment;

import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "后台管理系统公寓列表实体")
@EqualsAndHashCode(callSuper = true)
public class ApartmentItemVo extends ApartmentInfo {

    @Schema(description = "房间总数")
    private Long totalRoomCount;

    @Schema(description = "空闲房间数")
    private Long freeRoomCount;

}
