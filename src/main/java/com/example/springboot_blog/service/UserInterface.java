package com.example.springboot_blog.service;

import com.example.springboot_blog.common.pojo.response.JwtResponse;
import com.example.springboot_blog.common.pojo.response.UserInfoParam;
import com.example.springboot_blog.common.pojo.response.UserInfoResponse;
import jakarta.validation.constraints.NotNull;

public interface UserInterface {
    JwtResponse login( UserInfoParam user );

    UserInfoResponse getUserInfo( @NotNull Integer userId );

    UserInfoResponse getAurhorInfo( @NotNull Integer blogId );
}
