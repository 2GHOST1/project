package com.example.useractivity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.useractivity")
public class UserActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserActivityApplication.class, args);
	}

}
