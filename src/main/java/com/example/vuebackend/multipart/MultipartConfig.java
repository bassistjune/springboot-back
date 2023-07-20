package com.example.vuebackend.multipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class MultipartConfig {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10 * 1024 * 1024);  // 업로드할 파일의 최대 크기 설정
        return resolver;
    }

    @Bean
    public String uploadDir() {
        return uploadDir;
    }
}
