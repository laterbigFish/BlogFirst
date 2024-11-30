package com.example.springboot_blog.Controller;

import com.example.springboot_blog.Mapper.BlogInfoMapper;
import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
import com.example.springboot_blog.common.pojo.response.BlogResponse;
import com.example.springboot_blog.service.BlogInterface;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RequestMapping("/blog")
@RestController
public class BolgController {
    @Resource(name = "blogService")
    private BlogInterface blogInterface;
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    @GetMapping("/getList")
    public List<BlogResponse> getListBlog(){
       return   blogInterface.getListBlog();
    }
    @GetMapping("/getBlogDetail")
                                         //进行校验的注解
    public BlogResponse getBlogDetailById(@NotNull Integer blogId){
        log.info("getBlogDetailBy Id =" +  blogId);
     return blogInterface.getBlogDetailById(blogId);
    }

}
