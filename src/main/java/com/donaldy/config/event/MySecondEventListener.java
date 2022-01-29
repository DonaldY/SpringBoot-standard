package com.donaldy.config.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author donald
 * @date 2022/01/28
 */
@Slf4j
@Component
@Order(2)
public class MySecondEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        log.info("MySecondEventListener received: {}", event);
    }
}