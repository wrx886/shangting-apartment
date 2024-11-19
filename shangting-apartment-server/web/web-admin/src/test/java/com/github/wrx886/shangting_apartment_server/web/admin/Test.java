package com.github.wrx886.shangting_apartment_server.web.admin;

import com.github.wrx886.shangting_apartment_server.web.admin.config.MyBatisPlusMetaObjectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {
    @Autowired
    private MyBatisPlusMetaObjectHandler mybatisPlusInterceptor;
}
