package com.example.springboot_blog.service;

import com.example.springboot_blog.common.pojo.dataObject.JwtResponse;
import com.example.springboot_blog.common.pojo.dataObject.UserInfoParam;

public interface UserInterface {
    JwtResponse login( UserInfoParam user );
}
