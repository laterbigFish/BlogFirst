package com.example.springboot_blog.service;

import com.example.springboot_blog.common.pojo.Request.BlogUpdateParam;
import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
//import com.example.springboot_blog.common.pojo.response.AddBlogInfoParam;
import com.example.springboot_blog.common.pojo.response.AddBlogInfoParam;
import com.example.springboot_blog.common.pojo.response.BlogResponse;
import com.example.springboot_blog.common.pojo.response.UserInfoResponse;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface BlogInterface {
    List<BlogResponse> getListBlog();

    BlogResponse getBlogDetailById( Integer blogId );

    Boolean addBlog( AddBlogInfoParam addBlogInfoParam );

    Boolean updateBlog( BlogUpdateParam blogUpdateParam );

    Boolean DeleteBlog( Integer blogId );
}
