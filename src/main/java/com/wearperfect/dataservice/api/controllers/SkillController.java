package com.wearperfect.dataservice.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wearperfect.dataservice.api.dto.SkillBasicDetailsDTO;
import com.wearperfect.dataservice.api.dto.UserSkillsResponseDTO;
import com.wearperfect.dataservice.api.service.SkillService;

@RestController
public class SkillController {

	@Autowired
	SkillService skillService;

	@GetMapping(path = "/skills")
	List<SkillBasicDetailsDTO> getSkills() {
		return skillService.getSkills();
	}

	@GetMapping(path = "/users/{userId}/skills")
	UserSkillsResponseDTO getUserSkills(@PathVariable(name = "userId", required = true) Long userId) {
		return skillService.getUserSkills(userId);
	}

	@PostMapping(path = "/users/{userId}/skills/{skillId}")
	UserSkillsResponseDTO saveUserSkill(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "skillId", required = true) Integer skillId) {
		return skillService.saveUserSkill(userId, skillId);
	}

	@DeleteMapping(path = "/users/{userId}/skills/{skillId}")
	UserSkillsResponseDTO deleteUserSkill(@PathVariable(name = "userId", required = true) Long userId,
			@PathVariable(name = "skillId", required = true) Integer skillId){
		return skillService.deleteUserSkill(userId, skillId);
	}
	
}
