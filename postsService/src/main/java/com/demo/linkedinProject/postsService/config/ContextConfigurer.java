package com.demo.linkedinProject.postsService.config;

import com.demo.linkedinProject.postsService.contextHolder.AuthContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ContextConfigurer implements WebMvcConfigurer {

    @Autowired
    private AuthContextHolder contextHolder;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(contextHolder);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
