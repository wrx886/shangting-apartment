package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.LabelInfo;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomLabel;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【room_label(房间&标签关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.RoomLabel
*/
public interface RoomLabelMapper extends BaseMapper<RoomLabel> {
    // 根据房间 id 查询标签信息列表
    List<LabelInfo> selectByRoomId(Long id);
}




