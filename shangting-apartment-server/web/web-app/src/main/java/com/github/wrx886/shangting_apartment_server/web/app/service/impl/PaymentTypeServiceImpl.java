package com.github.wrx886.shangting_apartment_server.web.app.service.impl;

import com.github.wrx886.shangting_apartment_server.model.entity.PaymentType;
import com.github.wrx886.shangting_apartment_server.web.app.mapper.PaymentTypeMapper;
import com.github.wrx886.shangting_apartment_server.web.app.service.PaymentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author liubo
* @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
* @createDate 2023-07-26 11:12:39
*/
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
    implements PaymentTypeService {

}




