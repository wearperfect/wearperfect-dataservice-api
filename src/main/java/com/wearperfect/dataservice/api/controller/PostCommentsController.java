package com.wearperfect.dataservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.service.PostCommentService;

@RestController
public class PostCommentsController {

	@Autowired
	PostCommentService postCommentService; 

	@GetMapping(path = "/users/{userId}/posts/{postId}/comments")
	List<PostCommentDetailsDTO> getPostComments(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@RequestParam(name="page", required = false) Integer page) {
		return postCommentService.getComments(userId, postId, page);
	}

	@PostMapping(path = "/users/{userId}/posts/{postId}/comments")
	PostCommentDetailsDTO commentPost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId, @RequestBody PostCommentDTO postCommentDto) {
		return postCommentService.commentPost(userId, postId, postCommentDto);
	}

	@PutMapping(path = "/users/{userId}/posts/{postId}/comments/{commentId}")
	PostCommentDetailsDTO editPostComment(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@PathVariable(name = "commentId", required = true) Long commentId,
			@RequestBody PostCommentDTO postCommentDto) {
		return postCommentService.editPostComment(userId, postId, commentId, postCommentDto);
	}

	@DeleteMapping(path = "/users/{userId}/posts/{postId}/comments/{commentId}")
	Long deletePostComment(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@PathVariable(name = "commentId", required = true) Long commentId) {
		return postCommentService.deletePostComment(userId, postId, commentId);
	}
}
