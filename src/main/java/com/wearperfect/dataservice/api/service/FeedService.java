package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostDetailsDTO;

public interface FeedService {
	
	List<PostDetailsDTO> getFeed();
	
	List<PostDetailsDTO> getFeedByUserId(Long userId);
}
