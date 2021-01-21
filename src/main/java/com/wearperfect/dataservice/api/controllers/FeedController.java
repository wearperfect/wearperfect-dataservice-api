package com.wearperfect.dataservice.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.PostDTO;
import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.entities.Post;
import com.wearperfect.dataservice.api.mappers.PostDetailsMapper;
import com.wearperfect.dataservice.api.mappers.PostMapper;
import com.wearperfect.dataservice.api.service.FeedService;

@RestController
public class FeedController {

	@Autowired
	FeedService feedService;
	
	@Autowired
	PostDetailsMapper postDetailsMapper;
	
	@GetMapping(path = "/feed")
	List<PostDetailsDTO> getFeed(){
		return feedService.getFeed();
	}
	
	@GetMapping(path = "/users/{userId}/feed")
	List<PostDetailsDTO> getFeedBUserId(@PathVariable(name = "userId", required = true) Long userId){
		return feedService.getFeedByUserId(userId);
	}
}
