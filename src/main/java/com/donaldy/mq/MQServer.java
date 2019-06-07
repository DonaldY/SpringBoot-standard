package com.donaldy.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MQServer {

    @RabbitListener(queues = "mq-queue")
    public void receive(String message) {

        System.out.println(message);
    }
}
