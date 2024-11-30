package com.example.springboot_blog.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_blog.common.pojo.dataObject.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
