package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.PaymentType;
import com.github.wrx886.shangting_apartment_server.model.entity.RoomPaymentType;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【room_payment_type(房间&支付方式关联表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.RoomPaymentType
*/
public interface RoomPaymentTypeMapper extends BaseMapper<RoomPaymentType> {
    // 根据房间 id 查询支付方式列表
    List<PaymentType> selectByRoomId(Long id);
}




