package com.example.springboot_blog.common.interrupt;

import com.example.springboot_blog.common.Utils.JwtUntil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@Slf4j
public class LoginInterrupt implements HandlerInterceptor {
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception{
        //从header中获取token
        String jwtToken = request.getHeader("user_header_token");   //无法获取Token
        log.info("从header中获取token:{}",jwtToken);

        Claims claims = JwtUntil.parseToken(jwtToken);
        if (claims!=null){
            log.info("放⾏");
            return true;
        }
        response.setStatus(401);
        return false;
    }
}
