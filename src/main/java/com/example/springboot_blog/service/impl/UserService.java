package com.example.springboot_blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springboot_blog.Mapper.BlogInfoMapper;
import com.example.springboot_blog.Mapper.UserInfoMapper;
import com.example.springboot_blog.common.Utils.BeanConver;
import com.example.springboot_blog.common.Utils.JwtUntil;
import com.example.springboot_blog.common.exception.BlogException;
import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
import com.example.springboot_blog.common.pojo.response.JwtResponse;
import com.example.springboot_blog.common.pojo.dataObject.UserInfo;
import com.example.springboot_blog.common.pojo.response.UserInfoParam;
import com.example.springboot_blog.common.pojo.response.UserInfoResponse;
import com.example.springboot_blog.service.UserInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
//        log.error("***********  "+token);
        return new JwtResponse(userInfo.getId(),token);
    }

    @Override
    public UserInfoResponse getUserInfo( Integer userId ){
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        UserInfo userInfo = selectUserInfoById(userId);
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse getAurhorInfo( Integer blogId ){
        UserInfoResponse userInfoResponse = new UserInfoResponse();
//1. 根据博客ID, 获取作者ID
        BlogInfo blogInfo = blogInfoMapper.selectOne(new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getUserId, blogId)
                .eq(BlogInfo::getDeleteFlag, 0));
//2. 根据作者ID, 获取作者信息
        if (blogInfo == null){
            throw new BlogException("博客不存在");
        }
        UserInfo userInfo = selectUserInfoById(blogInfo.getUserId());
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    public UserInfo selectUserInfoByName(String userName) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserName,
                        userName).eq(UserInfo::getDeleteFlag, 0));
    }

    public UserInfo selectUserInfoById(Integer userId){
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getId, userId).eq(UserInfo::getDeleteFlag, 0));
    }
}
