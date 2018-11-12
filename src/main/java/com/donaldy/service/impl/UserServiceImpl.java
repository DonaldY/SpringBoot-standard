package com.donaldy.service.impl;

import com.donaldy.handler.AsyncException;
import com.donaldy.model.User;
import com.donaldy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Async
    @Override
    public void executeUserInfo(Integer userId) {
        log.info("enter executeUserInfo userId : {}, threadId : {}", userId, Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
            if (userId == 2) {
                throw new AsyncException(1, "123");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AsyncException e) {
            e.printStackTrace();
        }
        log.info("exit executeUserInfo userId : {}, threadId : {}", userId, Thread.currentThread().getId());
    }

    @Override
    public User getUser(String username, User _user) {
        System.out.println("input user : " + _user);
        System.out.println("username : " + username);
        User user = new User();
        user.setUsername("DonaldY");
        user.setPassword("123");

        return user;
    }

    @Override
    public User getUserFriend(String username) {
        // validateUserName(username);

        User user = new User();
        user.setUsername("go away, gay.");

        return user;
    }

    private void validateUserName(@NotBlank(message = "用户名不能为空")
                                  @Length(min = 3, max = 10, message = "用户名长度大于3小于等于10") String username) {
        System.out.println("go out, you are a gay.");
    }

}
