package com.example.springboot_blog.common.config;

import com.example.springboot_blog.common.interrupt.LoginInterrupt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterrupt loginInterrupt;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterrupt)
                .addPathPatterns("/user/**", "/blog/**")
                .excludePathPatterns("/user/login");
    }
}
