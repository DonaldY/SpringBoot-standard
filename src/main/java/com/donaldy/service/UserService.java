package com.donaldy.service;

import com.donaldy.model.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by DonaldY on 2018/7/11.
 */
@Validated
public interface UserService {

    public void executeUserInfo(Integer userId);

    public List<User> getUserListByName(@NotBlank(message = "用户名不能为空")
                                        @Length(min = 3, max = 10, message = "用户名长度大于3小于10")String username);

}
