package com.example.springboot_blog.common.Utils;

import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
import com.example.springboot_blog.common.pojo.response.BlogResponse;
import org.springframework.beans.BeanUtils;

public class BeanConver {
    public static BlogResponse trans( BlogInfo blogInfo ){
        BlogResponse blogInfoResponse = new BlogResponse();
        if (blogInfo!=null){
            BeanUtils.copyProperties(blogInfo, blogInfoResponse);
        }
        return blogInfoResponse;
    }

}
