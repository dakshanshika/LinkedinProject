package com.demo.linkedinProject.postsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class PostsServiceApplication {

	public static void main(String[] args) {
		System.out.println("JVM timezone :: " +  TimeZone.getDefault().getID());
		SpringApplication.run(PostsServiceApplication.class, args);
	}

}
