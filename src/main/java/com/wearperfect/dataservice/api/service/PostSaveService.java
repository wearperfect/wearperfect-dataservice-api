package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.PostSaveDTO;

public interface PostSaveService {
	
	Long savePost(Long userId, Long postId);
	
	Long unSavePost(Long userId, Long postId);
}
