package com.chatbot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class DemoMain {

	public static void main(String[] args) {
		SpringApplication.run(DemoMain.class, args);
	}

}
