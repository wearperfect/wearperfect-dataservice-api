package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.entities.PostItem;

public interface PostService {

	UserPostsResponseDTO getPostsByUserId(Long userId);

	UserPostsResponseDTO getLikedPostsByUserId(Long userId);

	UserPostsResponseDTO getSavedPostsByUserId(Long userId);

	UserPostsResponseDTO getTaggedPostsByUserId(Long userId);

	UserPostsResponseDTO getPostByUserIdAndPostId(Long userId, Long postId);

	UserPostsResponseDTO createPost(PostDTO postDto, Long postBy, String loggedInUsername);

	UserPostsResponseDTO createPostItems(List<PostItem> postItems, Long postId, Long userId);

	UserPostsResponseDTO deletePost(Long userId, Long postId);
}
