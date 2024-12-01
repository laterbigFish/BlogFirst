package com.example.springboot_blog.common.pojo.dataObject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
