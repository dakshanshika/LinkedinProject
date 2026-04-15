package com.demo.linkedinProject.userService.controller;


import com.demo.linkedinProject.userService.dto.LoginRequestDto;
import com.demo.linkedinProject.userService.dto.SignupRequestDto;
import com.demo.linkedinProject.userService.dto.UserDto;
import com.demo.linkedinProject.userService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class TestController {

    @Value("${myproperty}")
    private String myproperty;

    @GetMapping("/test")
    public String test(){
        return "Welocme to user service...with myproperty:" + myproperty;
    }


}
