package com.wearperfect.dataservice.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;

public interface PostService {

	UserPostsResponseDTO getPostsByUserId(Long userId);

	UserPostsResponseDTO getLikedPostsByUserId(Long userId);

	UserPostsResponseDTO getSavedPostsByUserId(Long userId);

	UserPostsResponseDTO getTaggedPostsByUserId(Long userId);

	UserPostsResponseDTO getPostByUserIdAndPostId(Long userId, Long postId);

	UserPostsResponseDTO createPost(Long postBy, String loggedInUsername, PostDetailsDTO postDetailsDTO, MultipartFile[] files);

	PostDTO deletePost(Long userId, Long postId);
}
