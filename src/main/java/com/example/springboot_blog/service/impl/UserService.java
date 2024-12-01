package com.example.springboot_blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.example.springboot_blog.Mapper.BlogInfoMapper;
import com.example.springboot_blog.Mapper.UserInfoMapper;
import com.example.springboot_blog.common.Utils.JwtUntil;
import com.example.springboot_blog.common.exception.BlogException;
import com.example.springboot_blog.common.pojo.dataObject.JwtResponse;
import com.example.springboot_blog.common.pojo.dataObject.UserInfo;
import com.example.springboot_blog.common.pojo.dataObject.UserInfoParam;
import com.example.springboot_blog.service.UserInterface;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserService implements UserInterface {

     @Autowired
     private UserInfoMapper userInfoMapper;
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @Override
    public JwtResponse login( UserInfoParam user ){
         //先判断是否存在
        UserInfo userInfo = selectUserInfoByName(user.getUserName());
        if(userInfo==null || userInfo.getId()==null){
           throw  new  BlogException("用户不存在");
        }
        if(!user.getPassword().equals(userInfo.getPassword())){
            throw new BlogException("⽤⼾密码不正确");
        }

        //验证成功进行获取token
        Map<String,Object> cliams = new HashMap<>();
        cliams.put("id", userInfo.getId());

        cliams.put("name", userInfo.getUserName());

        String token = JwtUntil.genToken(cliams);
        log.error("***********  "+token);
        return new JwtResponse(userInfo.getId(),token);
    }
    public UserInfo selectUserInfoByName(String userName) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserName,
                        userName).eq(UserInfo::getDeleteFlag, 0));
    }
}
