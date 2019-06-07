package com.donaldy.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {

        rabbitTemplate.convertAndSend("mq-queue", message);
    }
}
