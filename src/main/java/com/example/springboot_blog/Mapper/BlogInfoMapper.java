package com.example.springboot_blog.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogInfoMapper extends BaseMapper<BlogInfo> {

}
