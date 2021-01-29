package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostCommentDTO;
import com.wearperfect.dataservice.api.dto.PostCommentDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.PostLikeDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.entities.Master;
import com.wearperfect.dataservice.api.entities.PostItem;

public interface PostService {

	List<PostDetailsDTO> getPostsByUserId(Long userId);

	PostDetailsDTO getPostByUserIdAndPostId(Long userId, Long postId);

	PostDetailsDTO createPost(PostDTO postDto, Long postBy, String loggedInUsername);

	PostDetailsDTO createPostItems(List<PostItem> postItems, Long postId, Long userId);

	void deletePost(Long userId, Long postId);

	Master createMaster(Master master);
}
