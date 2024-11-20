package com.github.wrx886.shangting_apartment_server.web.admin.service.impl;

import com.github.wrx886.shangting_apartment_server.model.entity.FeeKey;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.FeeKeyMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.service.FeeKeyService;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【fee_key(杂项费用名称表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey>
        implements FeeKeyService {
    @Autowired
    private FeeKeyMapper feeKeyMapper;

    // 查询全部杂费名称和杂费值列表
    @Override
    public List<FeeKeyVo> feeInfoList() {
        return feeKeyMapper.feeInfoList();
    }
}
