package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entities.Post;

public interface FeedService {
	
	List<PostDetailsDTO> getFeed();
	
	List<PostDetailsDTO> getFeedByUserId(Long userId);
}
