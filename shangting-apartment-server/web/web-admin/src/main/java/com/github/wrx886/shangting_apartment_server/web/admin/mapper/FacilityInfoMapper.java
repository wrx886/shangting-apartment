package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.FacilityInfo;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.FacilityInfo
*/
public interface FacilityInfoMapper extends BaseMapper<FacilityInfo> {
    // 根据公寓 id 查询 查询配套列表
    List<FacilityInfo> selectListByApartmentId(Long id);
}




