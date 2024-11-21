package com.github.wrx886.shangting_apartment_server.web.admin.controller.apartment;

import com.github.wrx886.shangting_apartment_server.common.result.Result;
import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentInfo;
import com.github.wrx886.shangting_apartment_server.model.enums.ReleaseStatus;
import com.github.wrx886.shangting_apartment_server.web.admin.service.ApartmentInfoService;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentDetailVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentItemVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentQueryVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {
    @Autowired
    private ApartmentInfoService apartmentInfoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo) {
        apartmentInfoService.saveOrUpdateApartmentSubmitVo(apartmentSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<Page<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size,
            String queryVo) throws JsonMappingException, JsonProcessingException {
        Page<ApartmentItemVo> page = new Page<>(current, size);
        return Result.ok(apartmentInfoService.pageItem(page,
                objectMapper.readValue(
                        StringUtils.isBlank(queryVo) ? "{}" : queryVo, ApartmentQueryVo.class)));
    }

    @Operation(summary = "根据ID获取公寓详细信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok();
    }

    @Operation(summary = "根据id删除公寓信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        return Result.ok();
    }

    @Operation(summary = "根据id修改公寓发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(@RequestParam Long id, @RequestParam ReleaseStatus status) {
        return Result.ok();
    }

    @Operation(summary = "根据区县id查询公寓信息列表")
    @GetMapping("listInfoByDistrictId")
    public Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam Long id) {
        return Result.ok();
    }
}
