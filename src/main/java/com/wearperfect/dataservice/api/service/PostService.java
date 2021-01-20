package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.entities.Master;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.entities.PostItem;

public interface PostService {
	
	List<Post> getPostsByUserId(Long userId);
	
	Post getPostByUserIdAndPostId(Long userId, Long postId);
	
	Post createPost(Post post, Long postBy, String loggedInUsername);
	
	Post createPostItems(List<PostItem> postItems, Long postId, Long userId);
	
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
