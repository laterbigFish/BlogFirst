package com.example.springboot_blog.common.advice;


import com.example.springboot_blog.common.exception.BlogException;
import com.example.springboot_blog.common.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Result handler( Exception e){
        log.error("发生异常, e: {}", e);
        return Result.fail("发生错误"+e.getMessage());
    }

    @ExceptionHandler(BlogException.class)
    public Result blogExceptionHandler(Exception e){
        log.error("发生错误, e: {}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public Result handler(NoResourceFoundException e){
        log.error("文件不存在, e: {}", e.getResourcePath());
        return Result.fail("文件不存在:"+e.getResourcePath());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HandlerMethodValidationException.class, MethodArgumentNotValidException.class})
    public Result argHandler(Exception e){
        log.error("参数校验失败, e: {}", e.getMessage());
        return Result.fail("参数不合法");
    }


}