package com.donaldy.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by DonaldY on 2018/7/13.
 */
@Data
public class User {
    
    @NotBlank(message = "用户类中用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotNull(message = "")
    private Date birthday;

}
