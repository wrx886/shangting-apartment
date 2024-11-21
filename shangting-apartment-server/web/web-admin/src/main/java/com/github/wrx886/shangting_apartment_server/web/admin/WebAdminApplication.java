package com.github.wrx886.shangting_apartment_server.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication()
public class WebAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }

    // Jackson ObjectMapper
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
