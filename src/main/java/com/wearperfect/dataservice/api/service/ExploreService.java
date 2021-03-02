package com.wearperfect.dataservice.api.service;

import java.util.List;

import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;

public interface ExploreService {

	List<PostDetailsDTO> explorePosts();
	
	List<UserDetailsDTO> exploreDesigners();
	
	List<UserDetailsDTO> exploreBrands();
}
