package com.donaldy;

import com.donaldy.config.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnApplicationTests {

	@Autowired
	private AbstractApplicationContext applicationContext;

	@Test
	public void contextLoads() {

		SimpleApplicationEventMulticaster simpleApplicationEventMulticaster
				= applicationContext.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,
				SimpleApplicationEventMulticaster.class);
		simpleApplicationEventMulticaster.setErrorHandler(TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER);

		log.info("start to publish event");
		applicationContext.publishEvent(new MyEvent(UUID.randomUUID()));
		log.info("end to publish event");
	}
}
