package com.example.springboot_blog.common.pojo.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserInfoParam {
    @NotBlank(message = "⽤⼾名不能为空")
    @Length(max = 20, message = "⽤⼾名⻓度不能超过20")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @Length(max = 20, message = "密码⻓度不能超过20")
    private String password;
}
