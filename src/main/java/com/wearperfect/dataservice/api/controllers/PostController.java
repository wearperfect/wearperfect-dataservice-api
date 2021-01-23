package com.wearperfect.dataservice.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostLikeDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.entities.Master;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostComment;
import com.wearperfect.dataservice.api.mappers.PostMapper;
import com.wearperfect.dataservice.api.service.PostService;

@RestController
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	PostMapper postMapper;

	@GetMapping(path = "/users/{userId}/posts")
	List<PostDetailsDTO> postsByUserId(@PathVariable(name = "userId", required = true) Long userId) {
		return postService.getPostsByUserId(userId);
	}

	@PostMapping(path = "/users/{userId}/posts")
	PostDetailsDTO createPost(Authentication authentication ,@PathVariable(name = "userId", required = true) Long userId,
			@RequestBody PostDTO postDto) {
		return postService.createPost(postDto, userId, authentication.getName());
	}
	
	@PostMapping(path = "/masters")
	Master createMaster(@RequestBody Master master) {
		return postService.createMaster(master);
	}

	@GetMapping(path = "/users/{userId}/posts/{postId}")
	PostDetailsDTO postByUserId(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postService.getPostByUserIdAndPostId(userId, postId);
	}

	@DeleteMapping(path = "/users/{userId}/posts/{postId}")
	Long deletePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return null;
	}

	/**
	 * Post Action APIs begin here
	 **/
	@PostMapping(path = "/users/{userId}/posts/{postId}/like")
	PostLikeDTO likePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postService.likePost(userId, postId);
	}
	
	@DeleteMapping(path = "/users/{userId}/posts/{postId}/like")
	Long unLikePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postService.unLikePost(userId, postId);
	}

	@PostMapping(path = "/users/{userId}/posts/{postId}/save")
	PostSaveDTO savePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postService.savePost(userId, postId);
	}
	
	@DeleteMapping(path = "/users/{userId}/posts/{postId}/save")
	Long unSavePost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postService.unSavePost(userId, postId);
	}
	
	@GetMapping(path = "/users/{userId}/posts/{postId}/comments")
	List<PostCommentDetailsDTO> getPostComments(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId) {
		return postService.getComments(userId, postId);
	}

	@PostMapping(path = "/users/{userId}/posts/{postId}/comments")
	PostCommentDetailsDTO commentPost(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@RequestBody PostCommentDTO postCommentDto) {
		return postService.commentPost(userId, postId, postCommentDto);
	}

	@PutMapping(path = "/users/{userId}/posts/{postId}/comments/{commentId}")
	PostCommentDetailsDTO editPostComment(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@PathVariable(name = "commentId", required = true) Long commentId,
			@RequestBody PostCommentDTO postCommentDto) {
		return postService.editPostComment(userId, postId, commentId, postCommentDto);
	}

	@DeleteMapping(path = "/users/{userId}/posts/{postId}/comments/{commentId}")
	Long deletePostComment(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "postId", required = true) Long postId,
			@PathVariable(name = "commentId", required = true) Long commentId) {
		return postService.deletePostComment(userId, postId, commentId);
	}
}
