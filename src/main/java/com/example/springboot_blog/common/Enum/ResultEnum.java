package com.example.springboot_blog.common.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public enum ResultEnum {
    USCCESS(200),
    FAIL(-1);
    @Getter
    int code;
}
