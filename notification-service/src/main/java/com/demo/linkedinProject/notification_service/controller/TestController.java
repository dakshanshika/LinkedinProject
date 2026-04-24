package com.demo.linkedinProject.notification_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${spring.profiles.active}")
    private String activeProfile;


    @GetMapping("/test")
    public String welcome(){
        return "Welcome! You are in notification service : " + activeProfile;
    }
}
