package com.example.springboot_blog.common.pojo.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddBlogInfoParam {
@NotNull(message = "userId不能为空")
private Integer userId;
@NotBlank(message = "标题不能为空")
private String title;
@NotBlank(message = "内容不能为空")
private String content;
}