package com.wearperfect.dataservice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.StyleBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserStylesResponseDTO;
import com.wearperfect.dataservice.api.service.StyleService;

@RestController
public class StyleController {
	
	@Autowired
	StyleService styleService;
	
	@GetMapping(path = "/styles")
	List<StyleBasicDetailsDTO> getStyles(){
		return styleService.getStyles();
	}
	
	@GetMapping(path = "/users/{userId}/styles")
	UserStylesResponseDTO getUserStyles(@PathVariable(name = "userId", required = true) Long userId){
		return styleService.getUserStyles(userId);
	}
	
	@PostMapping(path = "/users/{userId}/styles/{styleId}")
	UserStylesResponseDTO saveUserStyle(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "styleId", required = true) Integer styleId){
		return styleService.saveUserStyle(userId, styleId);
	}
	
	@DeleteMapping(path = "/users/{userId}/styles/{styleId}")
	UserStylesResponseDTO deleteUserStyle(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "styleId", required = true) Integer styleId){
		return styleService.deleteUserStyle(userId, styleId);
	}
}
