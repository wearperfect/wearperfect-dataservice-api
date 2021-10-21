package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostSaveDTO;

public interface PostSaveService {

	List<PostSaveDTO> postSaves(Long postId);

	Boolean isPostSavedByUserId(Long userId, Long postId);

	Long savePost(Long userId, Long postId);

	Long unSavePost(Long userId, Long postId);
}
