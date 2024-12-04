package com.example.springboot_blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.springboot_blog.Mapper.BlogInfoMapper;
import com.example.springboot_blog.Mapper.UserInfoMapper;
import com.example.springboot_blog.common.Utils.BeanConver;
import com.example.springboot_blog.common.pojo.Request.BlogUpdateParam;
import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
import com.example.springboot_blog.common.pojo.dataObject.UserInfo;
import com.example.springboot_blog.common.pojo.response.AddBlogInfoParam;
import com.example.springboot_blog.common.pojo.response.BlogResponse;
import com.example.springboot_blog.common.pojo.response.UserInfoResponse;
import com.example.springboot_blog.service.BlogInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BlogService implements BlogInterface {

    @Autowired
    private BlogInfoMapper blogInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

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
        log.info("进到了getBlogDetailByID******");

        BlogInfo blogInfo = selectBlogById(blogId);
        System.out.println(blogInfo);
        if(blogInfo==null) return null;

        return BeanConver.trans(blogInfo);
    }

    @Override
    public Boolean addBlog( AddBlogInfoParam addBlogInfoParam ){

        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(addBlogInfoParam,blogInfo);
       try{
           int insert = blogInfoMapper.insert(blogInfo);
           if(insert==1) return true;
       }catch ( Exception e){
           log.error("是这个错误");
           log.error("发生错误");
       }

       return false;
    }

    @Override
    public Boolean updateBlog( BlogUpdateParam blogUpdateParam ){
        //更新数据
        BlogInfo blogInfo = new BlogInfo();
        blogInfo = BeanConver.UpdateTransBlog(blogUpdateParam);
        int update = blogInfoMapper.updateById(blogInfo);
        if(update>=1) return true;
        return false;
    }

    @Override
    public Boolean DeleteBlog( Integer blogId ){
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setDeleteFlag(1);
        blogInfo.setId(blogId);
       //逻辑删除
        int ret = 0;
        try{
            //真正删除
//            ret  = blogInfoMapper.deleteById(blogId);
            ret = blogInfoMapper.updateById(blogInfo);
            if(ret>=1) return true;
        } catch (Exception e) {
            log.error("updateBlog 发生异常 e= {}",e.getMessage());
        }
        return false;
    }


    public BlogInfo selectBlogById(Integer blogId){
        return blogInfoMapper.selectOne(new LambdaQueryWrapper<BlogInfo>()
                .eq(BlogInfo::getId, blogId).eq(BlogInfo::getDeleteFlag, 0));
    }

}
