package com.example.springboot_blog.Controller;

import com.example.springboot_blog.Mapper.BlogInfoMapper;
import com.example.springboot_blog.common.pojo.Request.BlogUpdateParam;
import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
import com.example.springboot_blog.common.pojo.response.AddBlogInfoParam;
import com.example.springboot_blog.common.pojo.response.BlogResponse;
import com.example.springboot_blog.service.BlogInterface;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForLocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/blog")
@RestController
public class BolgController {
    @Resource(name = "blogService")
    private BlogInterface blogInterface;
    @Autowired
    private BlogInfoMapper blogInfoMapper;
//    @Autowired
//    private BlogInfoMapper blogInfoMapper;

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
    @RequestMapping("/add")
    public Boolean addBlog( @Validated @RequestBody AddBlogInfoParam addBlogInfoParam ){
        return blogInterface.addBlog(addBlogInfoParam);
    }
    @RequestMapping(value = "/update",produces = "application/json")
    public Boolean updateBlog( @Validated @RequestBody BlogUpdateParam blogUpdateParam){
        return blogInterface.updateBlog(blogUpdateParam);
    }
    @RequestMapping("/delete")
    public Boolean DeleteBlog(@Validated Integer blogId){
        return blogInterface.DeleteBlog(blogId);
    }
}
