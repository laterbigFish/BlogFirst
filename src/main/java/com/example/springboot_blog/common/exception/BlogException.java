package com.example.springboot_blog.common.exception;

import lombok.Data;

@Data
//自定义异常  在统一异常中进行实现

public class BlogException extends RuntimeException{
    private int code;
    private String message;

    public BlogException() {
    }

    public BlogException(String message) {
        this.message = message;
    }

    public BlogException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}