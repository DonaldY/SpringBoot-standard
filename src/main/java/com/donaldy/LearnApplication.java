package com.donaldy;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class LearnApplication {

	@Bean
	public Queue MQQueue() {

		return new Queue("mq-queue");
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}

}
