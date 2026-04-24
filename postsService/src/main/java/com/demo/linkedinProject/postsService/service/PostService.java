package com.demo.linkedinProject.postsService.service;

import com.demo.linkedinProject.postsService.contextHolder.AuthContextHolder;
import com.demo.linkedinProject.postsService.dto.PersonDto;
import com.demo.linkedinProject.postsService.dto.PostCreateRequestDto;
import com.demo.linkedinProject.postsService.event.PostCreated;
import com.demo.linkedinProject.postsService.dto.PostDto;
import com.demo.linkedinProject.postsService.entity.Post;
import com.demo.linkedinProject.postsService.exception.ResourceNotFoundException;
import com.demo.linkedinProject.postsService.repository.PostRepository;
import com.demo.linkedinProject.postsService.restCall.ConnectionServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final ConnectionServiceClient connectionServiceClient;
    private final KafkaTemplate<Long,PostCreated> kafkaTemplate;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto) {
        Long userId = AuthContextHolder.getUserIdFromCH();
        log.info("Creating post for user with id: {}", userId);
       Post post = modelMapper.map(postCreateRequestDto, Post.class);
       post.setUserId(userId);
        post = postRepository.save(post);
        List<PersonDto> personDtoList = connectionServiceClient.getFirstDegreeConnections(userId, userId);

        for (PersonDto personDto: personDtoList){
            PostCreated postCreated = PostCreated.builder()
                    .content(post.getContent())
                    .postId(post.getId())
                    .ownerUserId(personDto.getUserId())
                    .userId(userId)
                    .build();

            String topic ="post_created_topic";
            kafkaTemplate.send(topic, postCreated);

        }
        return modelMapper.map(post,PostDto.class);
    }

    public PostDto getPostById(Long postId) {

        log.info("Getting the post with ID: {}", postId);

        Long userId = AuthContextHolder.getUserIdFromCH();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with ID: " + postId));

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
