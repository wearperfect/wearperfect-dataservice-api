package com.wearperfect.dataservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.PostLikeDTO;
import com.wearperfect.dataservice.api.service.PostLikeService;

@RestController
public class PostLikeController {

	@Autowired
	PostLikeService postLikeService;

	@GetMapping(path = "/posts/{postId}/likes")
	List<PostLikeDTO> postLikes(@PathVariable(name = "postId", required = true) Long postId) {
		return postLikeService.postLikes(postId);
	}

	@GetMapping(path = "/users/{userId}/posts/{postId}/likes")
	Boolean isPostLikedByUserId(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postLikeService.isPostLikedByUserId(userId, postId);
	}

	@PostMapping(path = "/users/{userId}/posts/{postId}/likes")
	Long likePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postLikeService.likePost(userId, postId);
	}

	@DeleteMapping(path = "/users/{userId}/posts/{postId}/likes")
	Long unLikePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postLikeService.unLikePost(userId, postId);
	}
}
