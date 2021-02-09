package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.SkillBasicDetailsDTO;
import com.wearperfect.dataservice.api.service.SkillService;

@RestController
public class SkillController {

	@Autowired
	SkillService skillService;
	
	@GetMapping(path = "/skills")
	List<SkillBasicDetailsDTO> getFeed(){
		return skillService.getSkills();
	}
	
	@GetMapping(path = "/users/{userId}/skills")
	List<SkillBasicDetailsDTO> getFeedBUserId(@PathVariable(name = "userId", required = true) Long userId){
		return skillService.getUserSkills(userId);
	}
}
