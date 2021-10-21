package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostLikeDTO;

public interface PostLikeService {
	
	List<PostLikeDTO> postLikes(Long userId);
	
	Boolean isPostLikedByUserId(Long userId, Long postId);

	Long likePost(Long userId, Long postId);
	
	Long unLikePost(Long userId, Long postId);

}
