package com.github.wrx886.shangting_apartment_server.web.admin.service;

import com.github.wrx886.shangting_apartment_server.model.entity.RoomInfo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomDetailVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomItemVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomQueryVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Service
 * @createDate 2023-07-24 15:48:00
 */
public interface RoomInfoService extends IService<RoomInfo> {

    // 保存或更新房间信息
    void savaOrUpdateRoomSubmitVo(RoomSubmitVo roomSubmitVo);

    // 根据条件分页查询房间列表
    IPage<RoomItemVo> pageRoomItemVo(IPage<RoomItemVo> page, RoomQueryVo queryVo);

    // 根据id获取房间详细信息
    RoomDetailVo getDetailById(Long id);

    // 根据id删除房间信息
    void removeByRoomId(Long id);
}
