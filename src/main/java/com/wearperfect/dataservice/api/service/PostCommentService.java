package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;

public interface PostCommentService {

	List<PostCommentDetailsDTO> getComments(Long userId, Long postId);
	
	PostCommentDetailsDTO commentPost(Long userId, Long postId, PostCommentDTO postCommentDto);
	
	PostCommentDetailsDTO editPostComment(Long userId, Long postId, Long commentId, PostCommentDTO postCommentDto);
	
	Long deletePostComment(Long userId, Long postId, Long commentId);
}
