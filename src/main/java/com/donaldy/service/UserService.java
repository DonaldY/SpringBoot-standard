package com.donaldy.service;

import com.donaldy.model.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by DonaldY on 2018/7/11.
 */
@Validated
public interface UserService {

    void executeUserInfo(Integer userId);

    User getUser(@NotBlank(message = "用户名不能为空")
                                        @Length(min = 3, max = 10, message = "用户名长度大于3小于10")String username,
                                        User user);

    User getUserFriend(String username);

    void recommendUser() throws ExecutionException, InterruptedException, TimeoutException;
}
