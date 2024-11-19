package com.github.wrx886.shangting_apartment_server.web.admin.custom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.github.wrx886.shangting_apartment_server.web.admin.custom.converter.StringToBaseEnumConverterFactory;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(stringToBaseEnumConverterFactory);
    }
}
