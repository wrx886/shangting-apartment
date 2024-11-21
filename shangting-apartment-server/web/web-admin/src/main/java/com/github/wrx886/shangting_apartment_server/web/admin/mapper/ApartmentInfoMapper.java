package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentInfo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentItemVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentQueryVo;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Mapper
 * @createDate 2023-07-24 15:48:00
 * @Entity com.atguigu.lease.model.ApartmentInfo
 */
public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {
    // 根据条件分页查询公寓列表
    Page<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo);
}
