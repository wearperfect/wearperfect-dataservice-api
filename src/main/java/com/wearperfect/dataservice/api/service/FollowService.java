package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.FollowDTO;
import com.wearperfect.dataservice.api.dto.UserBasicDetailsDTO;

public interface FollowService {

	FollowDTO followUser(Long followedBy, Long userId);

	List<UserBasicDetailsDTO> getUserFollowers(Long userId);

	List<UserBasicDetailsDTO> getUserFollowings(Long userId);

}
