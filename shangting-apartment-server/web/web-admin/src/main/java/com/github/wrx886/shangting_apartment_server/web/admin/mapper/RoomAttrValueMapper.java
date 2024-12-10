package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.RoomAttrValue;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.attr.AttrValueVo;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【room_attr_value(房间&基本属性值关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.RoomAttrValue
*/
public interface RoomAttrValueMapper extends BaseMapper<RoomAttrValue> {
    // 根据房间 id 查询属性信息列表
    List<AttrValueVo> selectByRoomId(Long id);
}




