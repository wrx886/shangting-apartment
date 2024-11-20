package com.github.wrx886.shangting_apartment_server.web.admin.controller.apartment;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.wrx886.shangting_apartment_server.common.result.Result;
import com.github.wrx886.shangting_apartment_server.model.entity.AttrKey;
import com.github.wrx886.shangting_apartment_server.model.entity.AttrValue;
import com.github.wrx886.shangting_apartment_server.web.admin.service.AttrKeyService;
import com.github.wrx886.shangting_apartment_server.web.admin.service.AttrValueService;
import com.github.wrx886.shangting_apartment_server.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {
    @Autowired
    private AttrKeyService attrKeyService;

    @Autowired
    private AttrValueService attrValueService;

    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result<Void> saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        attrKeyService.saveOrUpdate(attrKey);
        return Result.ok();
    }

    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result<Void> saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {
        attrValueService.saveOrUpdate(attrValue);
        return Result.ok();
    }

    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        return Result.ok(attrKeyService.listAttrInfo());
    }

    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    public Result<Void> removeAttrKeyById(@RequestParam Long attrKeyId) {
        // 删除属性名
        attrKeyService.removeById(attrKeyId);
        // 删除属性值
        attrValueService.remove(new LambdaQueryWrapper<AttrValue>().eq(AttrValue::getAttrKeyId, attrKeyId));
        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result<Void> removeAttrValueById(@RequestParam Long id) {
        attrValueService.removeById(id);
        return Result.ok();
    }
}
