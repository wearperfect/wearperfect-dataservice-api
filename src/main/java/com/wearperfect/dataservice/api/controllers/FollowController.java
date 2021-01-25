package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.FollowDTO;
import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.service.FollowService;

@RestController
public class FollowController {
	
	@Autowired
	FollowService followService;
	
	@GetMapping(value = "/users/{userId}/followers")
	@ResponseBody
	public List<UserBasicDetailsDTO> getUserFollowers(@PathVariable(name = "userId") Long followedBy,
			@PathVariable(name = "userId") Long userId) {
		return followService.getUserFollowers(userId);
	}
	
	@GetMapping(value = "/users/{userId}/following")
	@ResponseBody
	public List<UserBasicDetailsDTO> getUserFollowing(@PathVariable(name = "userId") Long followedBy,
			@PathVariable(name = "userId") Long userId) {
		return followService.getUserFollowing(userId);
	}
	
	@PostMapping(value = "/users/{followedBy}/follows/{userId}")
	@ResponseBody
	public FollowDTO followUser(@PathVariable(name = "followedBy") Long followedBy,
			@PathVariable(name = "userId") Long userId) {
		return followService.followUser(followedBy, userId);
	}
}
