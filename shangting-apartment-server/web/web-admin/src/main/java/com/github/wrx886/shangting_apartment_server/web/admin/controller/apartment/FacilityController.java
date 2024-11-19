package com.github.wrx886.shangting_apartment_server.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wrx886.shangting_apartment_server.common.result.Result;
import com.github.wrx886.shangting_apartment_server.model.entity.FacilityInfo;
import com.github.wrx886.shangting_apartment_server.model.enums.ItemType;
import com.github.wrx886.shangting_apartment_server.web.admin.service.FacilityInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "配套管理")
@RestController
@RequestMapping("/admin/facility")
public class FacilityController {
    @Autowired
    private FacilityInfoService facilityInfoService;

    @Operation(summary = "[根据类型]查询配套信息列表")
    @GetMapping("list")
    public Result<List<FacilityInfo>> listFacility(@RequestParam(required = false) ItemType type) {
        return Result.ok(facilityInfoService
                .list(new LambdaQueryWrapper<FacilityInfo>()
                        .eq(type != null, FacilityInfo::getType, type)));
    }

    @Operation(summary = "新增或修改配套信息")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody FacilityInfo facilityInfo) {
        facilityInfoService.saveOrUpdate(facilityInfo);
        return Result.ok();
    }

    @Operation(summary = "根据id删除配套信息")
    @DeleteMapping("deleteById")
    public Result<Void> removeFacilityById(@RequestParam Long id) {
        facilityInfoService.removeById(id);
        return Result.ok();
    }
}
