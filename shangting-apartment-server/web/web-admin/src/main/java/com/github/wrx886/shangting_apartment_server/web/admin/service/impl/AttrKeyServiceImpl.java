package com.github.wrx886.shangting_apartment_server.web.admin.service.impl;

import com.github.wrx886.shangting_apartment_server.model.entity.AttrKey;
import com.github.wrx886.shangting_apartment_server.web.admin.mapper.AttrKeyMapper;
import com.github.wrx886.shangting_apartment_server.web.admin.service.AttrKeyService;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
 * @createDate 2023-07-24 15:48:00
 */
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
        implements AttrKeyService {
    @Autowired
    private AttrKeyMapper attrKeyMapper;

    // 查询全部属性名称和属性值列表
    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return attrKeyMapper.listAttrInfo();
    }
}
