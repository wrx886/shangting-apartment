package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.FacilityInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomFacility;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author liubo
 * @description 针对表【room_facility(房间&配套关联表)】的数据库操作Mapper
 * @createDate 2023-07-24 15:48:00
 * @Entity com.atguigu.lease.model.RoomFacility
 */
public interface RoomFacilityMapper extends BaseMapper<RoomFacility> {
    // 根据房间 id 查询配套信息列表
    List<FacilityInfo> selectByRoomId(Long id);
}
