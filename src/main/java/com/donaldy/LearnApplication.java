package com.donaldy;

import com.donaldy.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class LearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}

	/*public static void main(String[] args) {

		ConfigurableApplicationContext context = new SpringApplicationBuilder(LearnApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		UserService userService = context.getBean(UserService.class);

		System.out.println("UserService Bean: " + userService);

		// 关闭上下文
		context.close();
	}*/

}
