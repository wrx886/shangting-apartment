package com.github.wrx886.shangting_apartment_server.web.admin.mapper;

import com.github.wrx886.shangting_apartment_server.model.entity.AttrKey;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.attr.AttrKeyVo;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.atguigu.lease.model.AttrKey
*/
public interface AttrKeyMapper extends BaseMapper<AttrKey> {
    // 查询全部属性名称和属性值列表
    List<AttrKeyVo> listAttrInfo();
}




