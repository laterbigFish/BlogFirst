package com.example.springboot_blog.service;

import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
import com.example.springboot_blog.common.pojo.response.BlogResponse;

import java.util.List;

public interface BlogInterface {
    List<BlogResponse> getListBlog();

    BlogResponse getBlogDetailById( Integer blogId );

}
