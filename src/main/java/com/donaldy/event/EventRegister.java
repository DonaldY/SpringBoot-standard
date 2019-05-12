package com.donaldy.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class EventRegister {

    @Autowired
    private ApplicationContext context;

    public void sendMessage(String message) {

        context.publishEvent(new ExceptionEvent(this, message));
    }
}
