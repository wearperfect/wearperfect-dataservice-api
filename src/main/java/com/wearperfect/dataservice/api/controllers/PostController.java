package com.wearperfect.dataservice.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.mappers.PostMapper;
import com.wearperfect.dataservice.api.service.PostService;

@RestController
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	PostMapper postMapper;

	@GetMapping(path = "/users/{userId}/posts")
	UserPostsResponseDTO postsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
		return postService.getPostsByUserId(userId);
	}

	@GetMapping(path = "/users/{userId}/posts/liked")
	UserPostsResponseDTO likedPostsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
		return postService.getLikedPostsByUserId(userId);
	}

	@GetMapping(path = "/users/{userId}/posts/saved")
	UserPostsResponseDTO savedPostsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
		return postService.getSavedPostsByUserId(userId);
	}

	@GetMapping(path = "/users/{userId}/posts/tagged")
	UserPostsResponseDTO taggedPostsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
		return postService.getTaggedPostsByUserId(userId);
	}

	@GetMapping(path = "/users/{userId}/posts/{postId}")
	UserPostsResponseDTO postByUserId(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postService.getPostByUserIdAndPostId(userId, postId);
	}

	@PostMapping(path = "/users/{userId}/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	UserPostsResponseDTO createPost(Authentication authentication,
			@PathVariable(name = "userId", required = true) Long userId, 
			@RequestPart(name = "files") MultipartFile[] files,
			@RequestPart(name = "data") PostDTO postDto) {
		return postService.createPost(postDto, userId, authentication.getName(), files);
	}

	@DeleteMapping(path = "/users/{userId}/posts/{postId}")
	UserPostsResponseDTO deletePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return null;
	}
}
