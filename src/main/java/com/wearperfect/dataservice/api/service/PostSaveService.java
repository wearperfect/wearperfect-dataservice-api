package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDTO;
import com.wearperfect.dataservice.api.dto.PostSaveDetailsDTO;

public interface PostSaveService {

	List<PostSaveDTO> postSaves(Long postId);

	Boolean isPostSavedByUserId(Long userId, Long postId);

	PostSaveDetailsDTO savePost(Long userId, Long postId);

	PostDTO unSavePost(Long userId, Long postId);
}
