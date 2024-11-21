package com.github.wrx886.shangting_apartment_server.web.admin.service;

import com.github.wrx886.shangting_apartment_server.model.entity.ApartmentInfo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentItemVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentQueryVo;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author liubo
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
* @createDate 2023-07-24 15:48:00
*/
public interface ApartmentInfoService extends IService<ApartmentInfo> {
    // 保存或更新公寓信息
    void saveOrUpdateApartmentSubmitVo(ApartmentSubmitVo apartmentSubmitVo);

    // 根据条件分页查询公寓列表
    Page<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo);
}
