package com.demo.linkedinProject.userService.service;

import com.demo.linkedinProject.userService.entity.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailService {

//    if we use any return type with @Async method then it throws RTE
//    Invalid return type for async method (only Future and void supported): class java.lang.String
//    String is bcz i used String as return type
    @Async
    public void trigerrOnBoardingEmail(User user) {
        System.out.println("sending email");

        System.out.println("Thread used in trigerrOnBoardingEmail() : " +Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        throw new RuntimeException("throwing error in async method()");

        System.out.println("email sent to : " + user.getEmail());

    }
}
