package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.RoomInfo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomItemVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.room.RoomQueryVo;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author liubo
 * @description 针对表【room_info(房间信息表)】的数据库操作Mapper
 * @createDate 2023-07-24 15:48:00
 * @Entity com.atguigu.lease.model.RoomInfo
 */
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {
    // 根据条件分页查询房间列表
    IPage<RoomItemVo> pageRoomItemVo(IPage<RoomItemVo> page, @Param("queryVo") RoomQueryVo queryVo);
}
