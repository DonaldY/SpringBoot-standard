package com.donaldy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by DonaldY on 2018/7/11.
 */
@Slf4j
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
}
