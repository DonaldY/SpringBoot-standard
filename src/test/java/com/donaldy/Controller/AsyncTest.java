package com.donaldy.Controller;

import com.donaldy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
public class AsyncTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAsyncVoid() throws Exception {

        userService.executeUserInfo(1);
        userService.executeUserInfo(2);
        userService.executeUserInfo(3);

    }
}
