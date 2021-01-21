package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entities.Master;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostItem;

public interface PostService {
	
	List<PostDetailsDTO> getPostsByUserId(Long userId);
	
	PostDetailsDTO getPostByUserIdAndPostId(Long userId, Long postId);
	
	PostDetailsDTO createPost(PostDTO postDto, Long postBy, String loggedInUsername);
	
	PostDetailsDTO createPostItems(List<PostItem> postItems, Long postId, Long userId);
	
	void deletePost(Long userId, Long postId);
	
	/**
	 * Post Action APIs begin here
	 **/
	PostDTO likePost(Long userId, Long postId, Boolean isLiked);
	
	PostDTO savePost(Long userId, Long postId, Boolean isSaved);
	
	PostDTO commentPost(Long userId, Long postId);
	
	PostDTO editPostComment(Long userId, Long postId, Long commentId);
	
	void deletePostComment(Long userId, Long postId, Long commentId);

	Master createMaster(Master master);
}
