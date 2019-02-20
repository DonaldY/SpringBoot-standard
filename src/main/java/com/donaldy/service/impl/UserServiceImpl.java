package com.donaldy.service.impl;

import com.donaldy.config.handler.AsyncException;
import com.donaldy.model.User;
import com.donaldy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.concurrent.*;

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

    /**
     * 改为同步非阻塞
     */
    @Override
    public void recommendUser() throws ExecutionException, InterruptedException {

        Future<User> familyFuture = findUserFamily();

        Future<User> friendsFuture = findUserFriends();

        Future<User> strangerFuture = findStranger();

        System.out.println(familyFuture.get());

        System.out.println(friendsFuture.get());

        System.out.println(strangerFuture.get());

    }

    private Future<User> findStranger() {

        loadMock("stranger", 3);

        return new AsyncResult<>(User.newBuilder().username("stranger").build());
    }

    private Future<User> findUserFriends() {

        loadMock("friend", 2);

        return new AsyncResult<>(User.newBuilder().username("friend").build());
    }

    private Future<User> findUserFamily() {

        loadMock("family", 1);

        return new AsyncResult<>(User.newBuilder().username("family").build());
    }

    private void loadMock(String source, int seconds) {
        try {
            long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(milliseconds);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.printf("[线程 : %s] %s 耗时 :  %d 毫秒\n",
                    Thread.currentThread().getName(), source, costTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
