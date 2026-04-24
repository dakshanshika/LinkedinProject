package com.demo.linkedinProject.postsService.ai;

import com.demo.linkedinProject.postsService.dto.PostDto;
import com.demo.linkedinProject.postsService.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostManagementTool {

    private final PostService postService;

    @Tool(description = "get the post by its Id")
    public PostDto hahaha(Long postId){
        return postService.getPostById(postId);
    }

}
