package com.example.springboot_blog.common.exception;

import com.example.springboot_blog.common.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class ErrorAdvice {
   @ExceptionHandler
    public Object handler(Exception e) {
       log.error("发生错误 e {}"+e.getMessage());
       return Result.fail("发生错误");
   }
   //还可以自己设置许多的异常

}
