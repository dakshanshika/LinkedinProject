package com.demo.linkedinProject.postsService.ai;

import com.demo.linkedinProject.postsService.dto.PostDto;
import com.demo.linkedinProject.postsService.entity.Post;
import com.demo.linkedinProject.postsService.service.PostService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    @Autowired
    private PostManagementTool postManagementTool;

    public ChatController(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @GetMapping("/chatWithMe")
    public String chat(@RequestParam(defaultValue = "ask how can I help you") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/postsChat")
    public String postsChat(@RequestParam String message){
        return chatClient.prompt()
                .user(message)
                .tools(postManagementTool)
                .call()
                .content();
    }

}
