package com.github.wrx886.shangting_apartment_server.web.app.vo.appointment;

import com.github.wrx886.shangting_apartment_server.model.entity.ViewAppointment;
import com.github.wrx886.shangting_apartment_server.web.app.vo.apartment.ApartmentItemVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "APP端预约看房详情")
public class AppointmentDetailVo extends ViewAppointment {

    @Schema(description = "公寓基本信息")
    private ApartmentItemVo apartmentItemVo;
}
