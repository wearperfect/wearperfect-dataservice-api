package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.service.FeedService;

@RestController
public class FeedController {

	@Autowired
	FeedService feedService;
	
	@GetMapping(path = "/feed")
	List<PostDetailsDTO> getFeed(){
		return feedService.getFeed();
	}
	
	@Cacheable(value="feed", key = "#userId")
	@GetMapping(path = "/users/{userId}/feed")
	List<PostDetailsDTO> getFeedBUserId(@PathVariable(name = "userId", required = true) Long userId){
		return feedService.getFeedByUserId(userId);
	}
}
