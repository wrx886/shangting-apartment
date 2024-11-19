package com.github.wrx886.shangting_apartment_server.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentFacility;
import com.github.wrx886.shangting_apartment_server.web.admin.service.ApartmentFacilityService;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.ApartmentFacilityMapper;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【apartment_facility(公寓&配套关联表)】的数据库操作Service实现
* @createDate 2023-07-24 15:48:00
*/
@Service
public class ApartmentFacilityServiceImpl extends ServiceImpl<ApartmentFacilityMapper, ApartmentFacility>
    implements ApartmentFacilityService{

}




