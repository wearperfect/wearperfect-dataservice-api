package com.wearperfect.dataservice.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.mappers.PostDetailsMapper;
import com.wearperfect.dataservice.api.mappers.PostMapper;
import com.wearperfect.dataservice.api.service.PostService;

@RestController
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	PostMapper postMapper;
	
	@Autowired
	PostDetailsMapper postDetailsMapper;

	@GetMapping(path = "/users/{userId}/posts")
	List<PostDetailsDTO> postsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
		List<Post> posts = postService.getPostsByUserId(userId);
		List<PostDetailsDTO> postsDtoList = posts.stream().map(post -> postDetailsMapper.mapPostToPostDetailsDto(post))
				.collect(Collectors.toList());
		return postsDtoList;
	}

	@GetMapping(path = "/users/{userId}/posts/{postId}")
	PostDTO postByUserId(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return null;
	}

	@PostMapping(path = "/users/{userId}/posts/{postId}")
	PostDTO createPost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return null;
	}

	@DeleteMapping(path = "/users/{userId}/posts/{postId}")
	void deletePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
	}

	/**
	 * Post Action APIs begin here
	 **/
	@PostMapping(path = "/users/{userId}/{postId}/like/{isLiked}")
	PostDTO likePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@PathVariable(name = "isLiked", required = true) Boolean isLiked) {
		return null;
	}

	@PostMapping(path = "/users/{userId}/{postId}/save/{isSaved}")
	PostDTO savePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@PathVariable(name = "isSaved", required = true) Boolean isSaved) {
		return null;
	}

	@PostMapping(path = "/users/{userId}/{postId}/comment")
	PostDTO commentPost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return null;
	}

	@PutMapping(path = "/users/{userId}/{postId}/comment/{commentId}")
	PostDTO editPostComment(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@PathVariable(name = "commentId", required = true) Long commentId) {
		return null;
	}

	@DeleteMapping(path = "/users/{userId}/{postId}/comment/{commentId}")
	void deletePostComment(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@PathVariable(name = "commentId", required = true) Long commentId) {
	}
}
