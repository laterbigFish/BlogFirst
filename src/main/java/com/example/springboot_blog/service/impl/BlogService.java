package com.example.springboot_blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot_blog.Mapper.BlogInfoMapper;
import com.example.springboot_blog.common.Utils.BeanConver;
import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
import com.example.springboot_blog.common.pojo.response.BlogResponse;
import com.example.springboot_blog.service.BlogInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService implements BlogInterface {

    @Autowired
    private BlogInfoMapper blogInfoMapper;
    @Override
    public List<BlogResponse> getListBlog(){
//        List<BlogInfo> blogInfos = blogMapper.selectList(new LambdaQueryWrapper<BlogInfo>()
//                .eq(BlogInfo::getDeleteFlag, 0).orderByDesc(BlogInfo::getId));
        List<BlogInfo> blogInfos = blogInfoMapper.selectList(new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getDeleteFlag, 0)
                .orderByDesc(BlogInfo::getId));
        //转化为我们想要的结果BlogResponse
        List<BlogResponse> blogInfoResponses = blogInfos.stream()
                .map(blogInfo -> BeanConver.trans(blogInfo)).collect(Collectors.toList());
        return blogInfoResponses;
    }

    @Override
    public BlogResponse getBlogDetailById(Integer blogId) {
        BlogInfo blogInfo = selectBlogById(blogId);

        if(blogInfo==null) return null;

        return BeanConver.trans(blogInfo);
    }
    /**git
     * 从数据库查询博客详情
     * @param blogId
     * @return
     */
    public BlogInfo selectBlogById(Integer blogId){
        return blogInfoMapper.selectOne(new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getId, blogId).eq(BlogInfo::getDeleteFlag, 0));
    }

}
