package com.github.wrx886.shangting_apartment_server.web.app.service.impl;

import com.github.wrx886.shangting_apartment_server.model.entity.BrowsingHistory;
import com.github.wrx886.shangting_apartment_server.web.app.mapper.BrowsingHistoryMapper;
import com.github.wrx886.shangting_apartment_server.web.app.service.BrowsingHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author liubo
 * @description 针对表【browsing_history(浏览历史)】的数据库操作Service实现
 * @createDate 2023-07-26 11:12:39
 */
@Service
public class BrowsingHistoryServiceImpl extends ServiceImpl<BrowsingHistoryMapper, BrowsingHistory>
        implements BrowsingHistoryService {
}