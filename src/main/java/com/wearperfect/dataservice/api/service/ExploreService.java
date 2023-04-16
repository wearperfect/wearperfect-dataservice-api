package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserProfileDTO;

public interface ExploreService {

	List<PostDetailsDTO> explorePosts();
	
	List<UserProfileDTO> exploreDesigners();
	
	List<UserProfileDTO> exploreBrands();
}
