package com.demo.linkedinProject.userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		System.out.println("JVM timezone :: " +  TimeZone.getDefault().getID());
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
