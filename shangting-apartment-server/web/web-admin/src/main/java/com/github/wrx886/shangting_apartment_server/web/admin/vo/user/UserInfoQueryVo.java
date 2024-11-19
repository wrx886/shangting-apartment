package com.github.wrx886.shangting_apartment_server.web.admin.vo.user;

import com.github.wrx886.shangting_apartment_server.model.enums.BaseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户信息查询实体")
@Data
public class UserInfoQueryVo {

    @Schema(description = "用户手机号码")
    private String phone;


    @Schema(description = "用户账号状态")
    private BaseStatus status;
}
