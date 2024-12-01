package com.example.springboot_blog.Controller;

import com.example.springboot_blog.common.pojo.dataObject.JwtResponse;
import com.example.springboot_blog.common.pojo.dataObject.UserInfoParam;
import com.example.springboot_blog.service.UserInterface;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource(name = "userService")
    private UserInterface userInterface;

    @RequestMapping("/login")
      //此处 以json的形式进行传递

    public JwtResponse getJwtKey(@Validated @RequestBody UserInfoParam user ){
        System.out.println();
        JwtResponse j = userInterface.login(user);
        return j;
    }
}
