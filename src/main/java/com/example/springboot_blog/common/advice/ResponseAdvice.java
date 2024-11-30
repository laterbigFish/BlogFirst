package com.example.springboot_blog.common.advice;

import com.example.springboot_blog.common.pojo.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
 //统一结果返回

      @Autowired
      private ObjectMapper objectMapper;
    @Override
    public boolean supports( MethodParameter returnType, Class converterType ){
        return true; //此处确定需要使用统一返回结果
    }

    @Override
    public Object beforeBodyWrite( Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response ){
        if(body instanceof Result<?>){
            return body;
        }
        if(body instanceof String) {
            try {
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return Result.success(body);
    }
}
