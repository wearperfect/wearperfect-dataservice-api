package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.entities.Post;

public interface PostService {
	
	List<Post> getPostsByUserId(Long userId);
	
	Post postByUserId(Long userId, Long postId);
	
	Post createPost(Long userId, Long postId);
	
	void deletePost(Long userId, Long postId);
	
	/**
	 * Post Action APIs begin here
	 **/
	PostDTO likePost(Long userId, Long postId, Boolean isLiked);
	
	PostDTO savePost(Long userId, Long postId, Boolean isSaved);
	
	PostDTO commentPost(Long userId, Long postId);
	
	PostDTO editPostComment(Long userId, Long postId, Long commentId);
	
	void deletePostComment(Long userId, Long postId, Long commentId);
}
