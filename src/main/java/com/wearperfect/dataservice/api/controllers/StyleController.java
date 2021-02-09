package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.StyleBasicDetailsDTO;
import com.wearperfect.dataservice.api.service.StyleService;

@RestController
public class StyleController {
	
	@Autowired
	StyleService styleService;
	
	@GetMapping(path = "/styles")
	List<StyleBasicDetailsDTO> getFeed(){
		return styleService.getStyles();
	}
	
	@GetMapping(path = "/users/{userId}/styles")
	List<StyleBasicDetailsDTO> getFeedBUserId(@PathVariable(name = "userId", required = true) Long userId){
		return styleService.getUserStyles(userId);
	}
}
