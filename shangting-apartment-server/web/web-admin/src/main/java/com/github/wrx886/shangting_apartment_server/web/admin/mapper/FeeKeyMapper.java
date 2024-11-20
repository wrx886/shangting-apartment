package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.FeeKey;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.FeeKey
*/
public interface FeeKeyMapper extends BaseMapper<FeeKey> {
    // 查询全部杂费名称和杂费值列表
    List<FeeKeyVo> feeInfoList();
}




