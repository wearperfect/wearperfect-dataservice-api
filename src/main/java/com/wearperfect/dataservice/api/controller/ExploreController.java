package com.wearperfect.dataservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.PostDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserDetailsDTO;
import com.wearperfect.dataservice.api.service.ExploreService;

@RestController
public class ExploreController {
	
	@Autowired
	ExploreService exploreService;

	@GetMapping(value= "/explore/posts")
	List<PostDetailsDTO> explorePosts(){
		return exploreService.explorePosts();
	}
	
	@GetMapping(value= "/explore/designers")
	List<UserDetailsDTO> exploreDesigners(){
		return exploreService.exploreDesigners();
	}
	
	@GetMapping(value= "/explore/brands")
	List<UserDetailsDTO> exploreBrands(){
		return exploreService.exploreBrands();
	}
}
