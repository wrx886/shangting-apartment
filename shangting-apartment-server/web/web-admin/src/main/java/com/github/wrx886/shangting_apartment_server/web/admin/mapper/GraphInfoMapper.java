package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.GraphInfo;
import com.github.wrx886.shangting_apartment_server.model.enums.ItemType;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.graph.GraphVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author liubo
 * @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
 * @createDate 2023-07-24 15:48:00
 * @Entity com.atguigu.lease.model.GraphInfo
 */
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {
    // 根据 itemType 和 itemId 查询 GraphInfo
    List<GraphVo> selectListByItemTypeAndId(@Param("id") Long id, @Param("itemType") ItemType apartment);
}
