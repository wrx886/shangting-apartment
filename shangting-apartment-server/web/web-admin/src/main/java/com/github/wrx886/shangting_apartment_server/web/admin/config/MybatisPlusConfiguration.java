package com.github.wrx886.shangting_apartment_server.web.admin.config;


import com.github.wrx886.shangting_apartment_server.model.config.MyBatisPlusBaseConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.github.wrx886.shangting_apartment_server.web.admin.mapper")
public class MybatisPlusConfiguration extends MyBatisPlusBaseConfig {
}
