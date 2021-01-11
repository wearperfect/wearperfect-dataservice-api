package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.entities.Post;

public interface FeedService {
	
	List<Post> getFeed();
	
	List<Post> getFeedByUserId(Long userId);
}
