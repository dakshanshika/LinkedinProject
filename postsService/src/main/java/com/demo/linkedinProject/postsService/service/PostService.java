package com.demo.linkedinProject.postsService.service;

import com.demo.linkedinProject.postsService.contextHolder.AuthContextHolder;
import com.demo.linkedinProject.postsService.dto.PersonDto;
import com.demo.linkedinProject.postsService.dto.PostCreateRequestDto;
import com.demo.linkedinProject.postsService.dto.PostDto;
import com.demo.linkedinProject.postsService.entity.Post;
import com.demo.linkedinProject.postsService.exception.ResourceNotFoundException;
import com.demo.linkedinProject.postsService.repository.PostRepository;
import com.demo.linkedinProject.postsService.restCall.ConnectionServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final ConnectionServiceClient connectionServiceClient;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        log.info("Creating post for user with id: {}", userId);
       Post post = modelMapper.map(postCreateRequestDto, Post.class);
       post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post,PostDto.class);
    }

    public PostDto getPostById(Long postId) {

        log.info("Getting the post with ID: {}", postId);

        String userId = AuthContextHolder.getUserIdFromCH();
        //calling connection service and sending the user id in the header
        List<PersonDto> personDtoList = connectionServiceClient.getFirstDegreeConnections(userId);


        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + postId));

        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        log.info("Getting all the posts of a user with ID: {}", userId);
        List<Post> listOfPostsByUserId= postRepository.findByUserId(userId);

        return listOfPostsByUserId.stream()
                .map(post-> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
