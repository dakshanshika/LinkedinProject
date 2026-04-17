package com.demo.linkedinProject.postsService.controller;

import com.demo.linkedinProject.postsService.contextHolder.AuthContextHolder;
import com.demo.linkedinProject.postsService.dto.PostCreateRequestDto;
import com.demo.linkedinProject.postsService.dto.PostDto;
import com.demo.linkedinProject.postsService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@Slf4j
@RequiredArgsConstructor
public class PostController {

    @Value("${my.property}")
    private String myproperty;

    private final PostService postService;

    private final AuthContextHolder authContextHolder;

//    @GetMapping("/test")
//    public ResponseEntity<String> welcome(@RequestHeader("X-User-Id") String userId){
//        return ResponseEntity.ok("Welcome : "+ userId);
//    }

//    @GetMapping("/test")
//    public ResponseEntity<String> welcome(HttpServletRequest request){
//        String userId = request.getHeader("X-User-Id");
//        return ResponseEntity.ok("Welcome : "+ userId);
//    }

    @GetMapping("/test")
    public ResponseEntity<String> welcome(){
        String userId = authContextHolder.getUserIdFromCH();
        return ResponseEntity.ok("Welcome : "+ userId);
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto,
                                              HttpServletRequest httpServletRequest) {
        PostDto postDto = postService.createPost(postCreateRequestDto, 1L);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        PostDto postDto = postService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsOfUser(@PathVariable Long userId) {
        List<PostDto> posts = postService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }

}
