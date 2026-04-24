package com.demo.linkedinProject.notification_service.event;

import lombok.Data;

@Data
public class PostCreated {
    private Long ownerUserId;
    private Long postId;
    private Long userId;
    private String content;
}