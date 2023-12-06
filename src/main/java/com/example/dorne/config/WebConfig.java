package com.example.dorne.config;

import com.example.dorne.interceptor.IPBlackListInterceptor;
import com.example.dorne.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoggingInterceptor());
        registry.addInterceptor(new IPBlackListInterceptor());
    }
}
