package com.github.wrx886.shangting_apartment_server.web.admin.controller.apartment;

import com.github.wrx886.shangting_apartment_server.common.result.Result;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomInfo;
import com.github.wrx886.shangting_apartment_server.model.enums.ReleaseStatus;
import com.github.wrx886.shangting_apartment_server.web.admin.service.RoomInfoService;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomDetailVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomItemVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomQueryVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "房间信息管理")
@RestController
@RequestMapping("/admin/room")
public class RoomController {

    @Autowired
    private RoomInfoService roomInfoService;

    @Operation(summary = "保存或更新房间信息")
    @PostMapping("saveOrUpdate")
    public Result<Void> saveOrUpdate(@RequestBody RoomSubmitVo roomSubmitVo) {
        roomInfoService.savaOrUpdateRoomSubmitVo(roomSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询房间列表")
    @GetMapping("pageItem")
    public Result<IPage<RoomItemVo>> pageItem(@RequestParam long current, @RequestParam long size,
            RoomQueryVo queryVo) {
        IPage<RoomItemVo> iPage = new Page<>(current, size);
        IPage<RoomItemVo> ret = roomInfoService.pageRoomItemVo(iPage, queryVo);
        return Result.ok(ret);
    }

    @Operation(summary = "根据id获取房间详细信息")
    @GetMapping("getDetailById")
    public Result<RoomDetailVo> getDetailById(@RequestParam Long id) {
        return Result.ok(roomInfoService.getDetailById(id));
    }

    @Operation(summary = "根据id删除房间信息")
    @DeleteMapping("removeById")
    public Result<Void> removeById(@RequestParam Long id) {
        roomInfoService.removeByRoomId(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改房间发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result<Void> updateReleaseStatusById(Long id, ReleaseStatus status) {
        roomInfoService.update(new LambdaUpdateWrapper<RoomInfo>()
                .eq(RoomInfo::getId, id)
                .set(RoomInfo::getIsRelease, status));
        return Result.ok();
    }

    @GetMapping("listBasicByApartmentId")
    @Operation(summary = "根据公寓id查询房间列表")
    public Result<List<RoomInfo>> listBasicByApartmentId(Long id) {
        return Result.ok(roomInfoService.list(new LambdaQueryWrapper<RoomInfo>()
                .eq(RoomInfo::getApartmentId, id)));
    }

}
