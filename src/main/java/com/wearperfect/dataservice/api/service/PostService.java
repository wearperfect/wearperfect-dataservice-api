package com.wearperfect.dataservice.api.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.UserPostsResponseDTO;
import com.wearperfect.dataservice.api.entities.PostMedia;

public interface PostService {

	UserPostsResponseDTO getPostsByUserId(Long userId);

	UserPostsResponseDTO getLikedPostsByUserId(Long userId);

	UserPostsResponseDTO getSavedPostsByUserId(Long userId);

	UserPostsResponseDTO getTaggedPostsByUserId(Long userId);

	UserPostsResponseDTO getPostByUserIdAndPostId(Long userId, Long postId);

	UserPostsResponseDTO createPost(Long postBy, String loggedInUsername, PostDTO postDto, MultipartFile[] files);

	UserPostsResponseDTO savePostMediaList(List<PostMedia> postMediaList, Long postId, Long userId);

	PostDTO deletePost(Long userId, Long postId);
}
