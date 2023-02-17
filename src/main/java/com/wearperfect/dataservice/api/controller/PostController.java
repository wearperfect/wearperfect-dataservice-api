package com.wearperfect.dataservice.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.mappers.PostMapper;
import com.wearperfect.dataservice.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostMapper postMapper;

    @GetMapping(path = "/v1/users/{userId}/posts")
    UserPostsResponseDTO getPostsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
        return postService.getPostsByUserId(userId);
    }

    @GetMapping(path = "/v1/users/{userId}/posts/liked")
    UserPostsResponseDTO getLikedPostsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
        return postService.getLikedPostsByUserId(userId);
    }

    @GetMapping(path = "/v1/users/{userId}/posts/saved")
    UserPostsResponseDTO getSavedPostsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
        return postService.getSavedPostsByUserId(userId);
    }

    @GetMapping(path = "/v1/users/{userId}/posts/tagged")
    UserPostsResponseDTO getTaggedPostsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
        return postService.getTaggedPostsByUserId(userId);
    }

    @GetMapping(path = "/users/{userId}/posts/{postId}")
    UserPostsResponseDTO getPostByUserIdAndPostId(@PathVariable(name = "userId", required = true) Long userId, @PathVariable(name = "postId", required = true) Long postId) {
        return postService.getPostByUserIdAndPostId(userId, postId);
    }

    @PostMapping(path = "/users/{userId}/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UserPostsResponseDTO createPost(Authentication authentication, @PathVariable(name = "userId", required = true) Long userId, @RequestPart(name = "data") String postDetailsDto, @RequestPart(name = "files") MultipartFile[] files) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PostDetailsDTO postObj = mapper.readValue(postDetailsDto, PostDetailsDTO.class);
        return postService.createPost(userId, authentication.getName(), postObj, files);
    }

    @DeleteMapping(path = "/users/{userId}/posts/{postId}")
    PostDTO deletePost(@PathVariable(name = "userId", required = true) Long userId, @PathVariable(name = "postId", required = true) Long postId) {
        return postService.deletePost(userId, postId);
    }
}
