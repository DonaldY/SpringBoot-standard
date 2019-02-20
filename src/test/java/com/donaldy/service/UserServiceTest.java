package com.donaldy.service;

import com.donaldy.LearnApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() throws InterruptedException, ExecutionException, TimeoutException {

        userService.recommendUser();
    }
}
