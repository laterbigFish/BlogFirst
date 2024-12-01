package com.example.springboot_blog.Controller;

import com.example.springboot_blog.common.pojo.response.JwtResponse;
import com.example.springboot_blog.common.pojo.response.UserInfoParam;
import com.example.springboot_blog.common.pojo.response.UserInfoResponse;
import com.example.springboot_blog.service.BlogInterface;
import com.example.springboot_blog.service.UserInterface;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource(name = "blogService")
    private BlogInterface blogInterface;
    @Resource(name = "userService")
    private UserInterface userInterface;

    @RequestMapping("/login")
      //此处 以json的形式进行传递

    public JwtResponse getJwtKey(@Validated @RequestBody UserInfoParam user ){
        System.out.println();
        JwtResponse j = userInterface.login(user);
        return j;
    }
    @RequestMapping("/getUserInfo")
    public UserInfoResponse getUserInfo( @NotNull Integer userId ){
       return userInterface.getUserInfo(userId);
    }

    @RequestMapping("/getAuthorInfo")
    public UserInfoResponse getAuthorInfo( @NotNull Integer blogId){
        return userInterface.getAurhorInfo(blogId);
    }
}
