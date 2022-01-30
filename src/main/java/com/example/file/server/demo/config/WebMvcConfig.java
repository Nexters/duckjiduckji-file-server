package com.example.file.server.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${filePath}")
    private String fileSavePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") // 요청 URL
                //.addResourceLocations("classpath:/static/"); // 실제 파일이 위치한 경로
                //.addResourceLocations("file:///" + fileSavePath); // 실제 파일이 위치한 경로 (윈도우)
                .addResourceLocations("file:" + fileSavePath + "/"); // 실제 파일이 위치한 경로 (리눅스) -> 맨뒤에 "/"를 붙여줘여됨...
    }
}
