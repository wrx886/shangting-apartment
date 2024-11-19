package com.github.wrx886.shangting_apartment_server.model.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.github.wrx886.shangting_apartment_server.web.admin.mapper", "com.github.wrx886.shangting_apartment_server.web.app.mapper"})
public class MybatisPlusConfiguration {
}
