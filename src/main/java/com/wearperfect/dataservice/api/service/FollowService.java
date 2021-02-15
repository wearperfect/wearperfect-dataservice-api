package com.wearperfect.dataservice.api.service;

import com.wearperfect.dataservice.api.dto.FollowDTO;
import com.wearperfect.dataservice.api.dto.UserFollowsResponseDTO;

public interface FollowService {

	FollowDTO followUser(Long followingBy, Long userId);
	
	FollowDTO unFollowUser(Long followingBy, Long userId);

	UserFollowsResponseDTO getUserFollowers(Long userId);

	UserFollowsResponseDTO getUserFollowings(Long userId);

}
