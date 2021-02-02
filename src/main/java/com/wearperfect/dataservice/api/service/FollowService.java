package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.FollowDTO;
import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserFollowUpDetailsDTO;

public interface FollowService {

	FollowDTO followUser(Long followingBy, Long userId);
	
	FollowDTO unFollowUser(Long followingBy, Long userId);

	List<UserFollowUpDetailsDTO> getUserFollowers(Long userId);

	List<UserFollowUpDetailsDTO> getUserFollowings(Long userId);

}
