package com.example.springboot_blog.common.pojo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Data
@Component
public class BlogResponse {
 private Integer id;
 private String title;
 private String content;
 private Integer userId;
 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //表示是按输出的格式
 private LocalDateTime createTime; //这里只是一个时间是哪一个时间无所谓
}
