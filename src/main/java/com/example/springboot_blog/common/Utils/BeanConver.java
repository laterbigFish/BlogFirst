package com.example.springboot_blog.common.Utils;

import com.example.springboot_blog.common.pojo.dataObject.BlogInfo;
import com.example.springboot_blog.common.pojo.dataObject.UserInfo;
import com.example.springboot_blog.common.pojo.response.BlogResponse;
import com.example.springboot_blog.common.pojo.response.UserInfoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.NullValueInNestedPathException;

public class BeanConver {
    public static BlogResponse trans( BlogInfo blogInfo ){
        BlogResponse blogInfoResponse = new BlogResponse();
        if (blogInfo!=null){
            BeanUtils.copyProperties(blogInfo, blogInfoResponse);
        }
        return blogInfoResponse;
    }

    public static UserInfoResponse UserInfoTransUserInfoResponse( UserInfo userInfo ){
        UserInfoResponse userInfoResponse = null;

        if(userInfo==null) return null;

        BeanUtils.copyProperties(userInfo,userInfoResponse);

        return userInfoResponse;
    }
}
