package com.donaldy.service;

import com.donaldy.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DonaldY on 2018/7/11.
 */
@Slf4j
@Validated
@Service
public class UserService {

    @Async
    public void executeUserInfo(Integer userId) {
        log.info("enter executeUserInfo userId : {}, threadId : {}", userId, Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("exit executeUserInfo userId : {}, threadId : {}", userId, Thread.currentThread().getId());
    }

    public List<User> getUserListByName(@NotBlank(message = "用户名不能为空")
                                        @Length(min = 3, max = 10, message = "用户名长度大于3小于10") String username) {
        System.out.println("username : " + username);
        List<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);
        return users;
    }
}
