package com.example.springboot_blog.common.pojo.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlogUpdateParam {
 @NotNull(message = "id is not null")
    private int id;
    @NotBlank(message = "title 不能为空")
    private String title;

    private String content;

}
