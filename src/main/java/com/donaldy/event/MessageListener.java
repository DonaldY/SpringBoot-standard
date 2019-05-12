package com.donaldy.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async
@Slf4j
@Component
public class MessageListener implements ApplicationListener<ExceptionEvent> {

    @Override
    public void onApplicationEvent(ExceptionEvent event) {

        log.info("发送消息开始 ===>");
        try {
            log.info("消息: " + event.getMessage());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("发送消息结束 ===>");
    }
}
